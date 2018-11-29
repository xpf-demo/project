package webSocket.socket;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webSocket.entity.Message;

@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
    
    //缓存session对应的用户  key为用户id，value为当前用户的下的所有session集合
    private static final Map<Long, Set<WebSocketSession>> userSocketSessionMap = new ConcurrentHashMap<>();
    
    //使用map来收集session，key为roomName，value为同一个房间的用户集合
//    private static final Map<String, Set<WebSocketSession>> roomsSocketSessionMap = new ConcurrentHashMap<>();

    /**
     * 账号上线后的处理
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long uid = (Long) session.getAttributes().get("uid");
        logger.info("Session {} connected.", uid);
        //如果是新用户就存储到map中
        boolean isNewUser = true;
        for (Object o : userSocketSessionMap.entrySet()) {
            Entry<?, ?> entry = (Entry<?, ?>) o;
            Long key = (Long) entry.getKey();
            if (key.equals(uid)) {
                userSocketSessionMap.get(uid).add(session);
                isNewUser = false;
                break;
            }
        }
        if (isNewUser) {
            Set<WebSocketSession> sessions = new HashSet<>();
            sessions.add(session);
            userSocketSessionMap.put(uid, sessions);
        }
        logger.info("当前在线人数: {}", userSocketSessionMap.values().size());
        
        /*//处理房间
        String roomName = (String)session.getAttributes().get("roomsName");
        //目前使用随机名称，可以整合自己的session管理，如shiro之类的
        //String name = randomName();

        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!roomsSocketSessionMap.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<WebSocketSession> room = new HashSet<>();
            // 添加用户
            room.add(session);
            roomsSocketSessionMap.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
        	roomsSocketSessionMap.get(roomName).add(session);
        }

        //向上线的人发送当前在线的人的列表
        Set<WebSocketSession> userList = roomsSocketSessionMap.get(roomName);
        session.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(userList)));;

        //向房间的所有人广播谁上线了
        broadcast(roomName, new TextMessage(new GsonBuilder().setDateFormat("").create().toJson(uid)));*/

        
    }

    /**
     * 客户端发送的消息，统一在这里处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength() == 0) {
            return;
        }
        Message msg = new Gson().fromJson(message.getPayload().toString(), Message.class);
        msg.setDate(new Date());
        broadcast(session,new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    /**
     * 发生异常后统一处理
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        //遍历当前map中的所有信息
        for (Set<WebSocketSession> item : userSocketSessionMap.values()) {
            if (item.contains(session)) {
            	  // 删除当前的session
                item.remove(session);
                //如果当前集合中的用户数为0，就删除当前集合
                if (0 == item.size()) {
                    userSocketSessionMap.values().remove(item);
                }
                break;
            }
        }
    }

    /**
     * 连接关闭后的处理
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("Session {} disconnected. Because of {}", session.getId(), closeStatus);
        for (Set<WebSocketSession> item : userSocketSessionMap.values()) {
            if (item.contains(session)) {
                // 删除当前的session
                item.remove(session);
                // 如果当前集合中的用户数为0，就删除当前集合
                if (0 == item.size()) {
                    userSocketSessionMap.values().remove(item);
                }
                break;
            }
        }
        logger.info("当前在线人数: {}", userSocketSessionMap.values().size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public void broadcast(final WebSocketSession nowSession,final TextMessage message) throws IOException {
    	if(nowSession != null) {
    		Long nowUid = (Long) nowSession.getAttributes().get("uid");
    		//遍历当前所有的在线用户
    		for(Set<WebSocketSession> item : userSocketSessionMap.values()) {
    			for (final WebSocketSession session : item) {
    				Long uid = (Long) session.getAttributes().get("uid");
    				if (session.isOpen() && !Objects.equals(uid, nowUid)) {
    					ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("socket-schedule-pool-%d").daemon(true).build());
    					executorService.execute(new Runnable() {
    						@Override
    						public void run() {
    							try {
    								if (session.isOpen()) {
    									logger.debug("broadcast output:" + message.toString());
    									session.sendMessage(message);
    								}
    							} catch (IOException e) {
    								logger.error(e.getMessage());
    							}
    						}
    					});
    				}
    			}
    		}
    	}else {
    		//遍历当前所有的在线用户
    		for(Set<WebSocketSession> item : userSocketSessionMap.values()) {
    			for (final WebSocketSession session : item) {
    				if (session.isOpen()) {
    					ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("socket-schedule-pool-%d").daemon(true).build());
    					executorService.execute(new Runnable() {
    						@Override
    						public void run() {
    							try {
    								if (session.isOpen()) {
    									logger.debug("broadcast output:" + message.toString());
    									session.sendMessage(message);
    								}
    							} catch (IOException e) {
    								logger.error(e.getMessage());
    							}
    						}
    					});
    				}
    			}
    		}
    		
    	}
    	
    }
    
    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
    	broadcast(null,message);
    }
    
    /**
     * 给某个用户发送信息
     * @param userName
     * @param message
     * @throws IOException
     */
    private void sendMessageToUser(Long uid, TextMessage message) throws IOException {
        for (Long id : userSocketSessionMap.keySet()) {
            if (id.equals(uid)) {
                for (WebSocketSession session : userSocketSessionMap.get(uid)) {
                    try {
                        logger.info("SendAll: {}", message);
                        session.sendMessage(message);
                    } catch (Exception e) {
                        logger.error(e.toString());
                    }
                }
            }
        }
    }
    
    
    /*// 按照房间名进行广播
    private void broadcast(String roomName, TextMessage msg) {
    	Set<WebSocketSession> sessions = roomsSocketSessionMap.get(roomName);
    	for(WebSocketSession session : sessions)  {
    		try {
				session.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        //将聊天记录加入缓存
        //这里需要将此服务层的bean手动注入
        if (chatCacheService == null) {
            chatCacheService = ApplicationContextRegister.getApplicationContext().getBean(ChatCacheService.class);
        }
        chatCacheService.cacheMsg(msg, roomName, CacheType.CONSULT);
    }*/


}

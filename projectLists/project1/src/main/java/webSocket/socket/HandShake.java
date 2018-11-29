package webSocket.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HandShake implements HandshakeInterceptor {
	/**
	 * 握手前拦截处理
	 */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,Map<String, Object> attributes) throws Exception {
        System.out.println("Websocket:账号[ID:"+ ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid")+ "]已连接上线！");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            //记录当前登录的账号id
            Long uid = (Long) session.getAttribute("uid");
            if (uid != null) {
            	attributes.put("uid", uid);
            } else {
                return false;
            }
        }
        return true;
    }

    
    /**
     * 握手后的拦截处理
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,Exception exception) {
    }

}

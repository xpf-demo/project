package webSocket.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import com.google.gson.GsonBuilder;

import webSocket.entity.Message;
import webSocket.entity.User;
import webSocket.socket.MyWebSocketHandler;

@Controller
@RequestMapping("/msg")
public class SocketController {

    
    private MyWebSocketHandler handler = new MyWebSocketHandler();

    Map<Long, User> users = new HashMap<>();

    @ModelAttribute
    public void setReqAndRes() {
        User u1 = new User();
        u1.setId(1L);
        u1.setName("张三");
        u1.setRoomsName("a");
        users.put(u1.getId(), u1);

        User u2 = new User();
        u2.setId(2L);
        u2.setName("李四");
        u2.setRoomsName("a");
        users.put(u2.getId(), u2);
        
        User u3 = new User();
        u3.setId(3L);
        u3.setName("王五");
        u3.setRoomsName("b");
        users.put(u3.getId(), u3);
        
        User u4 = new User();
        u4.setId(4L);
        u4.setName("赵柳");
        u4.setRoomsName("b");
        users.put(u4.getId(), u4);
  }

    // 登录
	@RequestMapping(value = "login")
    public ModelAndView doLogin(@RequestParam Long userId, HttpServletRequest request) {
        request.getSession().setAttribute("uid", userId);
        request.getSession().setAttribute("name", users.get(userId).getName());
        return new ModelAndView("redirect:talk");
    }

    //聊天页面
    @RequestMapping(value = "talk", method = RequestMethod.GET)
    public ModelAndView talk() {
        return new ModelAndView("talk");
    }

    //群发页面
    @RequestMapping(value = "broadcast", method = RequestMethod.GET)
    public ModelAndView broadcast() {
        return new ModelAndView("broadcast");
    }

    //群发短信
    @ResponseBody
    @RequestMapping(value = "broadcast", method = RequestMethod.POST)
    public void broadcast(String text) throws IOException {
        Message msg = new Message();
        msg.setDate(new Date());
        msg.setFrom(-1L);
        msg.setFromName("系统管理员");
        msg.setTo(0L);
        msg.setText(text);
        handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    //群发测试
    @ResponseBody
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test(@RequestParam("text") String text) throws IOException {
        Message msg = new Message();
        msg.setDate(new Date());
        msg.setFrom(-1L);
        msg.setFromName("系统管理员");
        msg.setTo(0L);
        msg.setText(text);
        String output = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg);
        System.out.println("output:" + output);
        handler.broadcast(new TextMessage(output));
    }

}




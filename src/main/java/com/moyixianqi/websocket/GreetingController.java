package com.moyixianqi.websocket;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    //实际上是app/hello
    //定位到服务端
    @MessageMapping("/hello")
    //定位到客户机
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception
    {
        Thread.sleep(1000);
        return new Greeting("你好, "+ HtmlUtils.htmlEscape(message.getName())+"!");
    }
}


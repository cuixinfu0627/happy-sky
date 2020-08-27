package com.happy.sky.controller;

import com.happy.sky.common.base.MapHandler;
import com.happy.sky.controller.request.param.RequestMessage;
import com.happy.sky.controller.request.param.ResponseMessage;
import com.happy.sky.view.R;
import com.happy.sky.web.WebSocketServer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名:WebSocketController <tb>
 * 描述:Socket  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018/10/19 10:19 <tb>
 */
@RestController
@RequestMapping("/socket")
public class SocketController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    //页面请求
    @GetMapping("/get/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/push/{cid}")
    public R pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(cid+"#"+e.getMessage());
        }
        return R.ok().put("cid",cid);
    }

    @RequestMapping(value="/pushVideoListToWeb",method= RequestMethod.POST,consumes = "application/json")
    public @ResponseBody
    Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param) {
        Map<String,Object> result =new HashMap<>();
        try {
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:"+ MapHandler.getLong4Map(param,"sltAccountId"),new Date().getTime()+"");
            result.put("operationResult", true);
        }catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;
    }

}

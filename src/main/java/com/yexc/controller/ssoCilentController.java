package com.yexc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yexc.common.GlobalSessions;
import com.yexc.common.connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/18.
 */

@Controller
public class ssoCilentController {
    Logger log= LoggerFactory.getLogger(ssoCilentController.class);
    @Value("#{configProperties['ssoServerLogin']}")
    private String ssoServerLogin;

    @Value("#{configProperties['ssoServerVerify']}")
    private String ssoServerVerify;

    @Value("#{configProperties['ssoServerLoginOut']}")
    private String ssoServerLoginOut;

    @Value("#{configProperties['returnUrl']}")
    private String returnUrl;

    @Value("#{configProperties['loginOutUrl']}")
    private String loginOutUrl;

    static Map<String,String> globalAndLocalIdMap=new HashMap<>();

    @RequestMapping("/user/login")
    public String login(HttpServletRequest request){
        String tempToken=request.getParameter("tempToken");
        HttpSession session=request.getSession();
        if(tempToken == null){
            //普通用户登录
            if(session.getAttribute("userName") == null){
                log.info("未登录");
                //未登录
                return "redirect:"+ssoServerLogin+"?returnUrl="+returnUrl;
            }
            else{
                //登录用户
                log.info("用户已登录");
                return "loginSucess";
            }
        }
        else{
            //服务端回调
            //验证临时令牌的正确
            log.info("验证临时令牌的正确性");
            JSONObject json=JSONObject.parseObject(verify(tempToken,request.getSession().getId()));
            String returnCode=json.getString("returnCode");
            if("500".equals(returnCode)){
                //令牌无效
                log.info("令牌无效");
                //重定向至登录中心
                return "redirect:"+ssoServerLogin+"?returnUrl="+returnUrl;
            }
            if("200".equals(returnCode)){
                //令牌有效
                String globalId=json.getString("globalId");
                String userName=json.getString("userName");
                //保存全局Id
                globalAndLocalIdMap.put(session.getId(),globalId);
                //和用户建立本地会话
                session.setAttribute("globalId",globalId);
                session.setAttribute("userName",userName);
                //登录用户
                log.info("令牌正确");
                log.info("globalAndLocalIdMap"+globalAndLocalIdMap);
                return "loginSucess";
            }
        }
        return null;
    }


    @RequestMapping("/user/loginOut")
    public String loginOut(HttpServletRequest request){
        try {
            String localSessionId=request.getParameter("localSessionId");
            log.info("用户退出 sessionId为"+localSessionId);
            if(localSessionId == null){
                log.info("本地用户通知客户端登出");
                //本地用户通知客户端登出
                localSessionId=request.getSession().getId();
                //通知认证中心退出
                globalLoginOut(localSessionId);
            }else{
                log.info("通知认证中心回调登出请求"+localSessionId);
            }
            //本地退出
            localSessionLoginOut(localSessionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }

    @RequestMapping("/user/test")
    public String test(HttpServletRequest request){
        System.out.println(ssoServerLogin);
        return "";
    }




    //验证令牌
    private String verify(String tempToken,String localSessionId){
        HashMap<String, String> map = new HashMap<>();
        map.put("tempToken",tempToken);
        map.put("localSessionId",localSessionId);
        map.put("loginOutUrl",loginOutUrl);
        String respone=connection.doPost(ssoServerVerify,map,"UTF-8");
        return respone;
    }

    //通知认证中心登出
    private String globalLoginOut(String localSessionId){
        String globalId=globalAndLocalIdMap.get(localSessionId);
        HashMap<String, String> map = new HashMap<>();
        map.put("globalId",globalId);
        String respone=connection.doPost(ssoServerLoginOut,map,"UTF-8");
        return respone;
    }

    //本地登出
    private void localSessionLoginOut(String localSessionId){
        HttpSession session=GlobalSessions.getSession(localSessionId);
        if(session != null){
            System.out.println(session);
            GlobalSessions.getSession(localSessionId).invalidate();
            globalAndLocalIdMap.remove(localSessionId);
        }
    }

}

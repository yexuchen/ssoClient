package com.yexc.common;

import com.sun.javafx.collections.MappingChange;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GlobalSessions {

    private static HashMap<String,HttpSession> SESSIONS=new HashMap<String, HttpSession>();

    public static void addSession(String sessionId,HttpSession session){
                    SESSIONS.put(sessionId,session);
    }

    public static HttpSession getSession(String sessionId){
        return SESSIONS.get(sessionId);
    }

    public static void delSession(String sessionId){
        SESSIONS.remove(sessionId);
    }
}

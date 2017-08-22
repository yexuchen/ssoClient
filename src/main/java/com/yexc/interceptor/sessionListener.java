package com.yexc.interceptor;

import com.yexc.common.GlobalSessions;
import com.yexc.controller.ssoCilentController;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Administrator on 2017/8/17.
 */
public class sessionListener implements HttpSessionListener{

    org.slf4j.Logger log= LoggerFactory.getLogger(sessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("creat session----");
        GlobalSessions.addSession(
                httpSessionEvent.getSession().getId(),
                httpSessionEvent.getSession()
        );
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("session销毁");
        GlobalSessions.delSession(httpSessionEvent.getSession().getId());
    }
}

package com.ptteng.LoginExit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener {

    int count = 0;

    @Override
//    创建一个session就增加一个用户
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

            ServletContext context = httpSessionEvent.getSession().getServletContext();
            Integer count = (Integer)context.getAttribute("count");
            if(count == null){
                count = 1;
                context.setAttribute("count",count);
            }else {
                count++;
                context.setAttribute("count",count);
            }



    }

//    销毁一个session就将在线人数减1
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        ServletContext context = httpSessionEvent.getSession().getServletContext();
        Integer count = (Integer)context.getAttribute("count");
        count--;
        context.setAttribute("count",count);


    }


}


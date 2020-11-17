package com.fertifa.app.adminSide.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.List;


public class OnlineAffiliateList implements HttpSessionAttributeListener {
    public static List<AF> usersList = new ArrayList<>();

    public static List<AF> getListUsers() {
        return usersList;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        synchronized (this) {
            if (httpSessionBindingEvent.getSession().getAttribute("email") != null && !usersList.contains(httpSessionBindingEvent.getSession().getAttribute("email"))) {
                usersList.add(new AF((Integer) httpSessionBindingEvent.getSession().getAttribute("AID"),
                        String.valueOf(httpSessionBindingEvent.getSession().getAttribute("email")),
                        String.valueOf(httpSessionBindingEvent.getSession().getAttribute("session"))));

            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getSession() == httpSessionBindingEvent.getSession().getAttribute("session")){
                usersList.remove(usersList.get(i));
            }
        }
        synchronized (this) {
            System.out.println("AID" + httpSessionBindingEvent.getSession().getAttribute("AID"));
            System.out.println("email" + httpSessionBindingEvent.getSession().getAttribute("email"));
            System.out.println("session" + httpSessionBindingEvent.getSession().getAttribute("session"));

        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
       /* synchronized (this) {
            if (httpSessionBindingEvent.getSession().getId() != null) {
                usersList.add(new AF(Integer.parseInt(String.valueOf(httpSessionBindingEvent.getSession().getAttribute("id"))),
                        String.valueOf(httpSessionBindingEvent.getSession().getAttribute("name")),
                        String.valueOf(httpSessionBindingEvent.getSession().getAttribute("session"))));
            }
        }*/
    }
}

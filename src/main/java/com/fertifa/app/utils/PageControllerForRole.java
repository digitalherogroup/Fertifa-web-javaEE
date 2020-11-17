package com.fertifa.app.utils;

import java.util.ArrayList;
import java.util.List;

public class PageControllerForRole {

    List<String> myPagesRoleUser = new ArrayList<>();

    public boolean PageRoleManager(int roleId, String PageName) {
        fillPages();
        int RoleByPage = getPageRoler(PageName);
        return RoleByPage == roleId;
    }

    private void fillPages() {
        myPagesRoleUser.add("/myjourney");
        myPagesRoleUser.add("/shoppingcart");
        myPagesRoleUser.add("/ordertesting");
        myPagesRoleUser.add("/bookapp");
        myPagesRoleUser.add("/myhealthhistory");
        myPagesRoleUser.add("/orders");
        myPagesRoleUser.add("/benefits");
        myPagesRoleUser.add("/educations");
        myPagesRoleUser.add("/education");
        myPagesRoleUser.add("/feedbackuser");
        myPagesRoleUser.add("/messaginguser");
    }

    private int getPageRoler(String pageName) {
        return myPagesRoleUser.contains(pageName.toLowerCase()) ? 3 : 2;
    }
}

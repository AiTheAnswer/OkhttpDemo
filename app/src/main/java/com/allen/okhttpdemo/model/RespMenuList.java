package com.allen.okhttpdemo.model;

import com.allen.okhttpdemo.datatrasfer.WinnerResponse;

import java.util.ArrayList;

/**
 * Created by Allen on 2018/3/13.
 */

public class RespMenuList extends Resp implements WinnerResponse {
    private ArrayList<RespMenu> menuList;

    public ArrayList<RespMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<RespMenu> menuList) {
        this.menuList = menuList;
    }
}

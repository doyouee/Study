package com.inzent.commonAPI.vo;

public class MenuVO {
    private String menu_id;
    private String menu_name;
    private String top_menu_id;
    private String sub_system;

    public MenuVO(String menu_id, String menu_name, String top_menu_id, String sub_system) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.top_menu_id = top_menu_id;
        this.sub_system = sub_system;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getTop_menu_id() {
        return top_menu_id;
    }

    public void setTop_menu_id(String top_menu_id) {
        this.top_menu_id = top_menu_id;
    }

    public String getSub_system() {
        return sub_system;
    }

    public void setSub_system(String sub_system) {
        this.sub_system = sub_system;
    }
}


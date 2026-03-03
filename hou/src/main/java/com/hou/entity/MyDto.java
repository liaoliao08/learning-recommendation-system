package com.hou.entity;

public class MyDto {
    private String belong;
    private int page;

    // 必须要有无参构造函数
    public MyDto() {
    }

    // 构造方法、getter和setter方法（可省略Lombok的@Data注解）
    public String getbelong() {
        return belong;
    }

    public void setbelong(String belong) {
        this.belong = belong;
    }

    public int getpage() {
        return page;
    }

    public void setpage(int page) {
        this.page = page;
    }


}
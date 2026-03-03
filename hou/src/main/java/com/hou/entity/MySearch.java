package com.hou.entity;

public class MySearch {
    private String name;
    private int currentPage;

    // 必须要有无参构造函数
    public MySearch() {
    }

    // 构造方法、getter和setter方法（可省略Lombok的@Data注解）
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getcurrentPage() {
        return currentPage;
    }

    public void setcurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
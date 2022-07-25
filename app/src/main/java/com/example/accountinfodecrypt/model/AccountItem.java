package com.example.accountinfodecrypt.model;

/**
 * Created by Nguyen Tuan Anh on 21/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class AccountItem {
    private int id;
    private String title;
    private String content;
    private String password;

    public AccountItem(int id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.demo.domain;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class User {

    private String name;
    private String email;
    private List<Item> itemList;

    public User(String name, String email, List<Item> itemList) {
        this.name = name;
        this.email = email;
        this.itemList = itemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}

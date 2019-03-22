package com.demo.querydsl;

import lombok.Builder;

public class AccountUserJoinDto {

    private String name;
    private Long age;
    private Double money;
    private String bankName;

    public AccountUserJoinDto(String name, Long age, Double money, String bankName) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.bankName = bankName;
    }
}

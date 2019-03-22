package com.demo.querydsl;

public class AccountUserJoinDto {

    private String userName;
    private Long age;
    private Double money;
    private String bankName;

    public AccountUserJoinDto(String userName, Long age, Double money, String bankName) {
        this.userName = userName;
        this.age = age;
        this.money = money;
        this.bankName = bankName;
    }
}

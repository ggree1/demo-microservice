package com.demo.querydsl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "account")
    private String account_num;

    @Column(name = "money")
    private Double money;

    @Column(name = "bank_name")
    private String bankName;

    @PrePersist
    public void initMoney() {
        this.money = 0D;
    }

    public Account(User user, String account_num, String bankName) {
        this.user = user;
        this.account_num = account_num;
        this.bankName = bankName;
    }

    public void changeMoney(Double money) {
        this.money = money;
    }

}

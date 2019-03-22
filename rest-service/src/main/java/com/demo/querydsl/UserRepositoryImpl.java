package com.demo.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> findUserByAge(Long age) {
        QUser user = QUser.user;
        return from(user)
                .where(user.age.eq(age))
                .limit(5)
                .fetch();
    }

    @Override
    public List<String> findUserNameByAge(Long age) {
        QUser user = QUser.user;
        return from(user)
                .where(user.age.eq(age))
                .limit(5)
                .select(user.name)
                .fetch();
    }

    @Override
    public List<AccountUserJoinDto> findAccountUserJoinData(String name, Long age, String email) {
        QUser user = QUser.user;
        QAccount account = QAccount.account;

        JPQLQuery<AccountUserJoinDto> query = from(account)
                .innerJoin(account.user, user)
                .select(Projections.constructor(AccountUserJoinDto.class,
                        user.name,
                        user.age,
                        account.money,
                        account.bankName))
                .where(containsName(user, name),
                        eqAge(user, age),
                        eqEmail(user, email));
        // Projections.bean(AccountUserJoinDto.class, user.name.as("userName"), user.age, account.money, account.bankName)
        return query.fetch();
    }

    private BooleanExpression containsName(QUser user, String name) {
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        return user.name.contains(name);
    }

    private BooleanExpression eqAge(QUser user, Long age) {
        if(age == null) return null;
        return user.age.eq(age);
    }

    private BooleanExpression eqEmail(QUser user, String email) {
        if(StringUtils.isEmpty(email)) {
          return null;
        }
        return user.email.eq(email);
    }
}

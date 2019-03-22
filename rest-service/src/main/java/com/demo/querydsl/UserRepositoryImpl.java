package com.demo.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
@Transactional(readOnly = true)
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> findAllUserByAge(Long age) {
        QUser user = QUser.user;
        return from(user)
                .where(user.age.eq(age))
                .limit(5)
                .fetch();
    }

    @Override
    public List<String> findAllUserNameByAge(Long age) {
        QUser user = QUser.user;
        return from(user)
                .where(user.age.eq(age))
                .limit(5)
                .select(user.name)
                .fetch();
    }

    @Override
    public List<AccountUserJoinDto> findAllUserByAgeGreaterThan(Long age) {
        QUser user = QUser.user;
        QAccount account = QAccount.account;

        JPQLQuery<AccountUserJoinDto> query = from(account)
                .innerJoin(account.user, user)
                .select(Projections.constructor(AccountUserJoinDto.class,
                        user.name,
                        user.age,
                        account.money,
                        account.bankName))
                .where(user.age.gt(age));

        return query.fetch();
    }
}

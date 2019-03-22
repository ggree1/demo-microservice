package com.demo.querydsl;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllUserByAge(Long age);
    List<String> findAllUserNameByAge(Long age);
    List<AccountUserJoinDto> findAllUserByAgeGreaterThan(Long age);

}

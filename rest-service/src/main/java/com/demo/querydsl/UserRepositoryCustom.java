package com.demo.querydsl;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findUserByAge(Long age);
    List<String> findUserNameByAge(Long age);
    List<AccountUserJoinDto> findAccountUserJoinData(String name, Long age, String email);

}

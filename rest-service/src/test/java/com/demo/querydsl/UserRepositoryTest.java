package com.demo.querydsl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@RunWith(SpringRunner.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User dk;
    private User lyn;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        accountRepository.deleteAll();

        dk = User.builder().name("dk").age(38L).email("dk@naver.com").build();
        lyn = User.builder().name("lyn").age(35L).email("lyn@naver.com").build();
        userRepository.save(dk);
        userRepository.save(lyn);

        IntStream.range(1, 11).forEach(i -> accountRepository.save(new Account(dk, "accNum"+i, "someBank")));
        IntStream.range(1, 11).forEach(i -> accountRepository.save(new Account(lyn, "accNum"+i, "someBank")));

        accountRepository.findAll().forEach(
                account -> System.out.println(":: " + account.toString())
        );
    }

    @Test
    public void testSavedData() throws Exception {
        Account persistedAccount = accountRepository.findById(3L).orElse(null);
        persistedAccount.changeMoney(350D);
        accountRepository.save(persistedAccount);
        Account thirdAcc = accountRepository.findById(3L).get();
        Account nonPersistedData = accountRepository.findById(100L).orElse(null);
        assertThat(thirdAcc.getMoney()).isEqualTo(350);
        assertThat(nonPersistedData).isNull();
    }

    @Test
    public void querydslUserRepositoryTest() throws Exception {
        User persistedDk = userRepository.findByName("dk").orElse(null);
        assertThat(persistedDk).isNotNull();
        assertThat(persistedDk.getEmail()).isEqualTo("dk@naver.com");

        User persistedLyn = userRepository.findByEmail("lyn@naver.com").orElse(null);
        assertThat(persistedLyn).isNotNull();
        assertThat(persistedLyn.getName()).isEqualTo("lyn");

        List<User> userListByAge = userRepository.findUserByAge(38L);
        assertThat(userListByAge.size()).isEqualTo(1);
        assertThat(userListByAge.get(0).getName()).isEqualTo("dk");

        List<String> nameListByAge = userRepository.findUserNameByAge(35L);
        assertThat(nameListByAge.size()).isEqualTo(1);
        assertThat(nameListByAge.get(0)).isEqualTo("lyn");

        List<User> nonList = userRepository.findUserByAge(10L);
        assertThat(nonList).isEmpty();

        List<AccountUserJoinDto> dtoList = userRepository.findAccountUserJoinData(null, 38L, null);
        assertThat(dtoList).isNotNull();
        assertThat(dtoList.size()).isEqualTo(10);
    }

}

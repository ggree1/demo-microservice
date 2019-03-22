package com.demo.querydsl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User dk;
    private User lyn;

    @Before
    public void setUp() {
        dk = User.builder().name("dk").age(38L).email("dk@naver.com").build();
        lyn = User.builder().name("lyn").age(35L).email("lyn@naver.com").build();
        userRepository.save(dk);
        userRepository.save(lyn);

        IntStream.range(1, 11).forEach(i -> accountRepository.save(new Account(dk, "accNum"+i, "someBank")));
        IntStream.range(1, 11).forEach(i -> accountRepository.save(new Account(lyn, "accNum"+i, "someBank")));

        Account thirdAcc = accountRepository.findById(3L).get();
        thirdAcc.changeMoney(new Double(350));
    }

    @Test
    public void testSavedData() throws Exception {
        Account persistedAccount = accountRepository.findById(3L).orElse(null);
        Account nonPersistedData = accountRepository.findById(100L).orElse(null);
        assertThat(persistedAccount.getMoney()).isEqualTo(350);
        assertThat(nonPersistedData).isNull();
    }

    /** todo
     *
     * querydsl 설정 정리 : build.gradle - querydsl - gradle (other-compileJava 로 빌드시 Q클래스 생성)
     * custom 설정시 쿼리메소드 및 querdydsl dto 사용시 오류 수정 / nameListByAge에서 user의 name만 가져오기 찾기
     */

    @Test
    public void querydslUserRepositoryTest() throws Exception {
        User persistedDk = userRepository.findByName("dk").orElse(null);
        assertThat(persistedDk).isNotNull();
        assertThat(persistedDk.getEmail()).isEqualTo("dk@naver.com");

        User persistedLyn = userRepository.findByEmail("lyn@naver.com").orElse(null);
        assertThat(persistedLyn).isNotNull();
        assertThat(persistedLyn.getName()).isEqualTo("lyn");

        List<User> userListByAge = userRepository.findAllUserByAge(38L);
        assertThat(userListByAge.size()).isEqualTo(1);
        assertThat(userListByAge.get(0).getName()).isEqualTo("dk");

        List<String> nameListByAge = userRepository.findAllUserNameByAge(35L);
        assertThat(nameListByAge.size()).isEqualTo(1);
        //assertThat(nameListByAge.get(0)).isEqualTo("lyn");
        System.out.println("::: " + nameListByAge.get(0));

        List<User> nonList = userRepository.findAllUserByAge(10L);
        assertThat(nonList).isEmpty();

        List<AccountUserJoinDto> dtoList = userRepository.findAllUserByAgeGreaterThan(10L);
        assertThat(dtoList).isNotNull();
        assertThat(dtoList.size()).isEqualTo(2);
    }

}

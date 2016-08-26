package chap06;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static springbook.user.service.UserServiceImpl.MIN_LOGIN_COUNT_FOR_SILVER;
import static springbook.user.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;

/**
 * @author jinyoung.park89
 * @since 8/26/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class TransactionTest {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("testID1", "jyp", "pw1", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@test.net"),
                new User("testID2", "jyp2", "pw2", Level.BASIC, MIN_LOGIN_COUNT_FOR_SILVER, 0, "test2@test.net"),
                new User("testID3", "jyp3", "pw3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1, "test3@test.net"),
                new User("testID4", "jyp4", "pw4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD, "test4@test.net"),
                new User("testID5", "jyp5", "pw5", Level.GOLD, 100, Integer.MAX_VALUE, "test5@test.net"));
    }

    @Test
    public void transactionSync() {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);

        userService.add(users.get(0));
        userService.add(users.get(1));
        assertThat(userDao.getCount(), is(2));

        transactionManager.rollback(txStatus);

        assertThat(userDao.getCount(), is(0));
    }
}

package chap04;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static springbook.user.service.UserServiceImpl.MIN_LOGIN_COUNT_FOR_SILVER;
import static springbook.user.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;

/**
 * @author jinyoung.park89
 * @since 7/29/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DataSource dataSource;

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

    @Test(expected = DuplicateKeyException.class)
    public void duplicateKey() {
        userDao.deleteAll();

        userDao.add(users.get(0));
        userDao.add(users.get(0));
    }

    @Test
    public void sqlExceptionTranslate() {
        userDao.deleteAll();

        try {
            userDao.add(users.get(0));
            userDao.add(users.get(0));
        } catch (DuplicateKeyException ex) {
            SQLException sqlEx = (SQLException) ex.getRootCause();
            SQLErrorCodeSQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
        }
    }

    @Test
    public void addAndGet() {

        userDao.deleteAll();

        User user1 = users.get(0);
        User user2 = users.get(1);

        userDao.add(user1);
        userDao.add(user2);

        User userget1 = userDao.get(user1.getId());
        User userget2 = userDao.get(user2.getId());

        checkSameUser(user1, userget1);
        checkSameUser(user2, userget2);
    }

    @Test
    public void update() {
        userDao.deleteAll();

        User user1 = users.get(0);

        userDao.add(user1);

        user1.setName("park jin young");
        userDao.update(user1);

        User user1update = userDao.get(user1.getId());
        checkSameUser(user1, user1update);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }
}

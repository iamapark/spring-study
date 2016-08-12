package chap06.mock.dao;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinyoung.park89
 * @since 8/1/16
 */
public class MockUserDao implements UserDao {

    private List<User> users;
    private List<User> updated = new ArrayList<>();

    private MockUserDao(List<User> users) {
        this.users = users;
    }

    public List<User> getUpdated() {
        return updated;
    }

    @Override
    public void update(User user) {
        updated.add(user);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void add(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }
}

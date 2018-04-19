package Service.Impl;/**
 * Created by pc on 2018/3/4.
 */

import Dao.UserDao;
import Po.FrontQuery;
import Service.UserService;
import Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/04
 */
@Service("UserService")
public class UserserviceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User searchbyid(String id) {
        return userDao.searchbyid(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public List<User> pagelist(int page, int pageSize) {
        return userDao.pagelist(new FrontQuery((page-1)*pageSize,pageSize));
    }

    @Override
    public long countall() {
        return userDao.countall();
    }

}

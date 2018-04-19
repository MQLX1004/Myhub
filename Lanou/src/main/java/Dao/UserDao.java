package Dao;

import Po.FrontQuery;
import Vo.Finish;
import Vo.User;

import java.io.Console;
import java.util.List;

/**
 * Created by pc on 2018/3/4.
 */
public interface UserDao {
    public User searchbyid(String id);
    public void add(User user);
    public List<User> list();
    public void update(User user);
    public void delete(String id);
    public List<User> pagelist(FrontQuery frontQuery);
    public long countall();
}

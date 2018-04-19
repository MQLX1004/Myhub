package Service;

import Vo.Finish;
import Vo.User;

import java.util.List;

/**
 * Created by pc on 2018/3/4.
 */
public interface UserService {
    public User searchbyid(String id);
    public void add(User user);
    public List<User> list();
    public void update(User user);
    public void delete(String id);
    public List<User> pagelist(int page, int pageSize);
    public long countall();
}

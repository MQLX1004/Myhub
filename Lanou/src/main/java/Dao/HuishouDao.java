package Dao;

import Po.FrontQuery;
import Vo.Huishou;
import Vo.User;

import java.util.List;

/**
 * Created by pc on 2018/3/10.
 */
public interface HuishouDao {
    public Huishou searchbyid(String id);
    public void add(Huishou huishou);
    public List<Huishou> list();
    public void update(Huishou huishou);
    public void delete(String id);
    public List<Huishou> pagelist(FrontQuery frontQuery);
    public long countall();
}

package Service;

import Vo.Finish;
import Vo.Huishou;

import java.util.List;

/**
 * Created by pc on 2018/3/10.
 */
public interface HuishouService {
    public Huishou searchbyid(String id);
    public void add(Huishou huishou);
    public List<Huishou> list();
    public void update(Huishou huishou);
    public void delete(String id);
    public long countall();
    public List<Huishou> pagelist(int page, int pageSize);
}

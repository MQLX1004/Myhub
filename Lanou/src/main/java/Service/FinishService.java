package Service;

import Vo.Finish;

import java.util.List;

/**
 * Created by pc on 2018/3/23.
 */
public interface FinishService {
    public void add(Finish finish);
    public List<Finish> list();
    public void update(Finish finish);
    public List<Finish> listbyhsid(String huishouid);
    public int countbyhs(String huishouid);
    public long countall();
    public List<Finish> pagelist(int page,int pageSize);

}

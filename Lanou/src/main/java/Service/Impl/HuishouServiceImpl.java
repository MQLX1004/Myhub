package Service.Impl;/**
 * Created by pc on 2018/3/10.
 */


import Dao.HuishouDao;
import Po.FrontQuery;
import Service.HuishouService;
import Vo.Huishou;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/10
 */
@Service("HuishouService")
public class HuishouServiceImpl implements HuishouService {
    @Autowired
    HuishouDao huishouDao;

    @Override
    public Huishou searchbyid(String id) {
        return huishouDao.searchbyid(id);
    }

    @Override
    public void add(Huishou huishou) {
        huishouDao.add(huishou);
    }

    @Override
    public List<Huishou> list() {
        return huishouDao.list();
    }

    @Override
    public void update(Huishou huishou) {
        huishouDao.update(huishou);
    }

    @Override
    public void delete(String id) {
        huishouDao.delete(id);
    }

    @Override
    public long countall() {
        return huishouDao.countall();
    }

    @Override
    public List<Huishou> pagelist(int page, int pageSize) {
        return huishouDao.pagelist(new FrontQuery((page-1)*pageSize,pageSize));
    }
}

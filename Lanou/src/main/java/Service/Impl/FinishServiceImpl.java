package Service.Impl;/**
 * Created by pc on 2018/3/23.
 */

import Dao.FinishDao;
import Po.FrontQuery;
import Service.FinishService;
import Vo.Finish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/23
 */
@Service("FinishService")
public class FinishServiceImpl implements FinishService {
    @Autowired
    FinishDao finishDao;
    @Override
    public void add(Finish finish) {
        finishDao.add(finish);
    }

    @Override
    public List<Finish> list() {
        return finishDao.list();
    }

    @Override
    public void update(Finish finish) {
        finishDao.update(finish);
    }

    @Override
    public List<Finish> listbyhsid(String huishouid) {
        return finishDao.listbyhsid(huishouid);
    }

    @Override
    public int countbyhs(String huishouid) {
        return finishDao.countbyhs(huishouid);
    }

    @Override
    public long countall() {
        return finishDao.countall();
    }

    @Override
    public List<Finish> pagelist(int page, int pageSize) {
        return finishDao.pagelist(new FrontQuery((page-1)*pageSize,pageSize));
    }
}

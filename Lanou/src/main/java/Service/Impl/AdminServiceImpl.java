package Service.Impl;/**
 * Created by pc on 2018/3/16.
 */

import Dao.AdminDao;
import Service.AdminService;
import Vo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/16
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    public Admin getByUserName(String name) {
        return adminDao.getByUserName(name);
    }
}

package Dao;

import Vo.Admin;

/**
 * Created by pc on 2018/3/16.
 */
public interface AdminDao {
    public Admin getByUserName(String name);
}

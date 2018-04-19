package Realm;/**
 * Created by pc on 2018/3/16.
 */

import Service.AdminService;
import Vo.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/16
 */
/**
 *为当前登录用户授予角色和权限
 *
 * */
public class Myrealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String adminname = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }
    /**
     * 验证当前登录用户
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String)authenticationToken.getPrincipal();
        Admin admin = adminService.getByUserName(name);
        if(admin!=null){
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getName(),admin.getPassword(),"xxx");
            return authenticationInfo;
        }
        else{
        return null;
        }
    }
}

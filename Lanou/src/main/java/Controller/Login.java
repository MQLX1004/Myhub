package Controller;/**
 * Created by pc on 2018/3/10.
 */

import Vo.Admin;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/10
 */
@Controller
public class Login {
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public String login(Admin admin){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getName(),admin.getPassword());
        try{
            subject.login(token);
            return "main";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/tologin";
        }

    }
    @RequestMapping("/tologin")
    public String tologin(){
        return "Login";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}

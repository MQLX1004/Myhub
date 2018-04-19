package Controller;/**
 * Created by pc on 2018/3/4.
 */

import Service.HuishouService;
import Service.UserService;
import Vo.Huishou;
import Vo.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/04
 */
@Controller
public class Submit {
    @Autowired
    UserService userService;
    @Autowired
    HuishouService huishouService;
    @RequestMapping(value = "/submit",method = {RequestMethod.POST})
    public String submit(User user){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(uuid.substring(0,4));
        userService.add(user);
        return "success";
    }
    @RequestMapping(value = "/mainsubmit",method = {RequestMethod.POST},produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String mainsubmit(User user){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(uuid.substring(0,4));
        userService.add(user);
        return JSON.toJSONString("提交成功");
    }
    @RequestMapping(value = "/hssubmit",method = {RequestMethod.POST},produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String hssubmit(Huishou huishou){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        huishou.setId(uuid.substring(0,4));
        huishouService.add(huishou);
        return JSON.toJSONString("提交成功");
    }
}

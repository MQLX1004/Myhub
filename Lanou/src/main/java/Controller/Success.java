package Controller;/**
 * Created by pc on 2018/3/5.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/05
 */
@Controller
public class Success {
    @RequestMapping("/success")
    public String success(){
        System.out.println("success");
        return "success";
    }
}

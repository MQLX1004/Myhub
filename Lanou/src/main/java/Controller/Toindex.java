package Controller;/**
 * Created by pc on 2018/3/4.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/04
 */
@Controller
public class Toindex {
    @RequestMapping("/index")
    public String toindex(){
        return "index";
    }
}

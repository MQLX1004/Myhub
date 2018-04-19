package Controller;/**
 * Created by pc on 2018/3/23.
 */

import Po.DataGrid;
import Service.FinishService;
import Service.HuishouService;
import Service.UserService;
import Vo.Finish;
import Vo.Huishou;
import Po.Mychart;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/23
 */
@Controller
public class FinishController {
    @Autowired
    FinishService finishService;
    @Autowired
    HuishouService huishouService;
    @Autowired
    UserService userService;

    @RequestMapping("/tofinishlist")
    public String tofinishlist(){
        return "FinishList";
    }

    @RequestMapping(value="/finishinsert",method = {RequestMethod.POST},produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String insert(Finish finish){
        Huishou huishou = huishouService.searchbyid(finish.getHuishouid());
        finish.setHuishouname(huishou.getName());
        finish.setHuishouphone(huishou.getPhone());
        finishService.add(finish);
        return JSON.toJSONString("添加成功");
    }

    @RequestMapping(value="/finishUpdate",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String Finishupdate(Finish finish){
        finishService.update(finish);
        return JSON.toJSONString("修改成功");
    }

    @RequestMapping(value="/finishlist",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String FinishList(int page,int rows){
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(finishService.countall());
        dataGrid.setRows(finishService.pagelist(page,rows));
        return JSON.toJSONString(dataGrid);
    }

    @RequestMapping(value="/listbyhsid",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String Listbyhsid(String huishouid){
        return JSON.toJSONString(finishService.listbyhsid(huishouid));
    }

    @RequestMapping(value="/getfinishdate",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String getfinishdate() throws ParseException {
        List<Date> list = new ArrayList<Date>();
        List<Finish> flist = new ArrayList<Finish>();
        flist = finishService.list();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for(int i=0;i<flist.size();i++){
            Date date = dateFormat.parse(flist.get(i).getFinishdate());
            list.add(date);
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping(value="/countbyhs",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String countbyhsid(){
        List<Mychart> list = new ArrayList<Mychart>();
        for(int i=0;i<huishouService.list().size();i++){
            if(huishouService.list().get(i).getStatus().equals("是")) {
                Mychart mychart = new Mychart();
                mychart.setName(huishouService.list().get(i).getId());
                String huishouid = huishouService.list().get(i).getId();
                mychart.setValue(finishService.countbyhs(huishouid));
                list.add(mychart);
            }
        }
        return JSON.toJSONString(list);
    }

}

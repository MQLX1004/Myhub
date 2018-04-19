package Controller;/**
 * Created by pc on 2018/3/15.
 */

import Po.DataGrid;
import Service.HuishouService;
import Vo.Huishou;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author xxx
 * @date4 {YEAR}/03/15
 */
@Controller
public class HuishouController {
    @Autowired
    HuishouService huishouService;
    @RequestMapping(value = "/tohsList",produces = {"text/json;charset=UTF-8"})
    public String tohs(){
        return "HsList";
    }

    @RequestMapping(value="/HsList",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String UserList(int page,int rows){
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(huishouService.countall());
        dataGrid.setRows(huishouService.pagelist(page,rows));
        return JSON.toJSONString(dataGrid);
    }

    @RequestMapping(value="/HsidList",produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String HsidList(){
        return JSON.toJSONString(huishouService.list());
    }
    @RequestMapping(value = "/HsDelete",method = {RequestMethod.POST},produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String delete(String id){
        huishouService.delete(id);
        return JSON.toJSONString("删除成功！！");
    }

    @RequestMapping(value = "/HsUpdate",method = {RequestMethod.POST},produces = {"text/json;charset=UTF-8"})
    public String update(Huishou huishou){
        huishouService.update(huishou);
        return JSON.toJSONString("更新成功");
    }
}

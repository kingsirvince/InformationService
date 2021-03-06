package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DockInfo;
import com.company.project.model.distance.DockInfoDistance;
import com.company.project.service.DockInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* Created by CodeGenerator on 2019/08/12.
*/
@RestController
@RequestMapping("/dock/info")
public class DockInfoController {
    @Resource
    private DockInfoService dockInfoService;

    @PostMapping("/add")
    public Result add(DockInfo dockInfo,@RequestParam(defaultValue = "requestId:0") String requestId) {
        dockInfoService.save(dockInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        dockInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(DockInfo dockInfo,@RequestParam(defaultValue = "requestId:0") String requestId) {
        dockInfoService.update(dockInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        DockInfo dockInfo = dockInfoService.findById(id);
        return ResultGenerator.genSuccessResult(dockInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "requestId:0") String requestId) {
        PageHelper.startPage(page, size);
        List<DockInfo> list = dockInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    /**
     * 按条件查询   (例如查询“梧桐作业区“）
     * @param fieldName  代表字段名（Model中的成员变量，DockInfo）   fieldName=dockName
     * @param value      可以不是唯一的（返回多个List）              value=梧桐作业区
     */
    @PostMapping("/findByCondition")
    public Result findByCondition(@RequestParam String fieldName, @RequestParam Object value,@RequestParam(defaultValue = "requestId:0") String requestId) {

        Condition condition = new Condition(DockInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo(fieldName, value);

        List<DockInfo> list = dockInfoService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("sortByDistance")
    public Result sortByDistance(@RequestParam("longitude") BigDecimal longitude, @RequestParam ("latitude")BigDecimal latitude,@RequestParam(value = "num",defaultValue ="5" )Integer num ,@RequestParam(defaultValue = "requestId:0") String requestId){
        List<DockInfoDistance> list = dockInfoService.sortByDistance(longitude, latitude,num);
        return ResultGenerator.genSuccessResult(list);
    }



}

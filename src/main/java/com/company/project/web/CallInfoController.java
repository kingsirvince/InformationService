package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CallInfo;
import com.company.project.service.CallInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/08/12.
*/
@RestController
@RequestMapping("/call/info")
public class CallInfoController {
    @Resource
    private CallInfoService callInfoService;

    @PostMapping("/getLatest")
    public Result getLatest(@RequestParam(defaultValue = "requestId:0") String requestId) {
        List<CallInfo> list = callInfoService.getLatest();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 按条件查询   (例如查询“梧桐作业区“）
     * @param fieldName  代表字段名（Model中的成员变量，DockInfo）   fieldName=dockName
     * @param value      可以不是唯一的（返回多个List）              value=梧桐作业区
     */
    @PostMapping("/findByCondition")
    public Result findByCondition(@RequestParam String fieldName, @RequestParam Object value,@RequestParam(defaultValue = "requestId:0") String requestId) {

        Condition condition = new Condition(CallInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo(fieldName, value);

        List<CallInfo> list = callInfoService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 新增报警
     * @param callInfo
     * @param requestId
     * @return
     */
    @PostMapping("/add")
    public Result add(CallInfo callInfo,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callInfoService.save(callInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改报警
     * @param callInfo    id=?&Call_grade=0  （修改报警已结束）
     * @param requestId
     * @return
     */
    @PostMapping("/update")
    public Result update(CallInfo callInfo,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callInfoService.update(callInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        CallInfo callInfo = callInfoService.findById(id);
        return ResultGenerator.genSuccessResult(callInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "requestId:0") String requestId) {
        PageHelper.startPage(page, size);
        List<CallInfo> list = callInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CallUpload;
import com.company.project.service.CallUploadService;
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
* Created by CodeGenerator on 2019/08/15.
*/
@RestController
@RequestMapping("/call/upload")
public class CallUploadController {
    @Resource
    private CallUploadService callUploadService;

    /**
     * 查询未处理报警
     * @param requestId 发起请求的ID
     * @return          查询call_upload中未处理的报警，callGrade=1，返回List<CallUpload>
     */
    @PostMapping("/findUnhandledAlarm")
    public Result findUnhandledAlarm(@RequestParam(defaultValue = "requestId:0") String requestId) {

        Condition condition = new Condition(CallUpload.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("callGrade", "1");

        List<CallUpload> list = callUploadService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 按条件查询   (例如查询“梧桐作业区“）
     * @param fieldName  代表字段名（Model中的成员变量，DockInfo）   fieldName=dockName
     * @param value      可以不是唯一的（返回多个List）              value=梧桐作业区
     */
    @PostMapping("/findByCondition")
    public Result findByCondition(@RequestParam String fieldName, @RequestParam Object value,@RequestParam(defaultValue = "requestId:0") String requestId) {

        Condition condition = new Condition(CallUpload.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo(fieldName, value);

        List<CallUpload> list = callUploadService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }
    @PostMapping("/add")
    public Result add(CallUpload callUpload,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callUploadService.save(callUpload);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callUploadService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改报警
     * @param callUpload  id=？&callGrade=0
     * @param requestId
     * @return
     */
    @PostMapping("/update")
    public Result update(CallUpload callUpload,@RequestParam(defaultValue = "requestId:0") String requestId) {
        callUploadService.update(callUpload);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id,@RequestParam(defaultValue = "requestId:0") String requestId) {
        CallUpload callUpload = callUploadService.findById(id);
        return ResultGenerator.genSuccessResult(callUpload);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam(defaultValue = "requestId:0") String requestId) {
        PageHelper.startPage(page, size);
        List<CallUpload> list = callUploadService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

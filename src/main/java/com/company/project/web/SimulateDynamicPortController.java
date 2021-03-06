package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SimulateDynamicPort;
import com.company.project.service.SimulateDynamicPortService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/07/31.
*/
@RestController
@RequestMapping("/simulate/dynamic/port")
public class SimulateDynamicPortController {
    @Resource
    private SimulateDynamicPortService simulateDynamicPortService;

    @PostMapping("/add")
    public Result add(SimulateDynamicPort simulateDynamicPort) {
        simulateDynamicPortService.save(simulateDynamicPort);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        simulateDynamicPortService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SimulateDynamicPort simulateDynamicPort) {
        simulateDynamicPortService.update(simulateDynamicPort);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SimulateDynamicPort simulateDynamicPort = simulateDynamicPortService.findById(id);
        return ResultGenerator.genSuccessResult(simulateDynamicPort);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SimulateDynamicPort> list = simulateDynamicPortService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

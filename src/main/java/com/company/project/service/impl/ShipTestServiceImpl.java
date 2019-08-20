package com.company.project.service.impl;

import com.company.project.dao.ShipTestMapper;
import com.company.project.model.ShipTest;
import com.company.project.service.ShipTestService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/08/14.
 */
@Service
@Transactional
public class ShipTestServiceImpl extends AbstractService<ShipTest> implements ShipTestService {
    @Resource
    private ShipTestMapper shipTestMapper;

}

package com.company.project.service.impl;

import com.company.project.dao.FloodgateInfoMapper;
import com.company.project.model.FloodgateInfo;
import com.company.project.model.distance.FloodgateInfoDistance;
import com.company.project.service.FloodgateInfoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/08/12.
 */
@Service
@Transactional
public class FloodgateInfoServiceImpl extends AbstractService<FloodgateInfo> implements FloodgateInfoService {
    @Resource
    private FloodgateInfoMapper floodgateInfoMapper;

    @Override
    public List<FloodgateInfoDistance> sortByDistance(BigDecimal longitude, BigDecimal latitude, Integer num) {
        return floodgateInfoMapper.sortByDistance(longitude, latitude, num);
    }
}
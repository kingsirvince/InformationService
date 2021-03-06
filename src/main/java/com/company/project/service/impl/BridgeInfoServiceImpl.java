package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.BridgeInfoMapper;
import com.company.project.model.BridgeInfo;
import com.company.project.model.distance.BridgeInfoDistance;
import com.company.project.model.other.BridgeInfoHeight;
import com.company.project.service.BridgeInfoService;
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
public class BridgeInfoServiceImpl extends AbstractService<BridgeInfo> implements BridgeInfoService {
    @Resource
    private BridgeInfoMapper bridgeInfoMapper;

    @Override
    public List<BridgeInfoDistance> sortByDistance(BigDecimal longitude, BigDecimal Latitude, Integer num) {

        return bridgeInfoMapper.sortByDistance(longitude, Latitude, num);
    }

    @Override
    public Integer updateLimitHeight(Double subtrahend) {
        return bridgeInfoMapper.updateLimitHeight(subtrahend);
    }

    @Override
    public Double getByRowField(String field, String row, String rowValue) {
        System.out.println(field);
        return bridgeInfoMapper.getByRowField(field, row, rowValue);
    }

    @Override
    public List<BridgeInfoHeight> getLimitHeight() {
        return bridgeInfoMapper.getLimitHeight();
    }
}

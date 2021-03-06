package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.BridgeInfo;
import com.company.project.model.distance.BridgeInfoDistance;
import com.company.project.model.other.BridgeInfoHeight;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/08/12.
 */
public interface BridgeInfoService extends Service<BridgeInfo> {

    List<BridgeInfoDistance> sortByDistance(BigDecimal longitude, BigDecimal Latitude, Integer num);
    Integer updateLimitHeight( Double subtrahend);
   Double getByRowField(String field, String row, String rowValue);
    List<BridgeInfoHeight> getLimitHeight();
}

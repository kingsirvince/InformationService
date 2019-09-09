package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Coordinate;
import com.company.project.model.ShipInfo;
import com.company.project.service.ShipInfoService;
import com.company.project.service.ShipUploadService;
import com.company.project.util.ChannelDivisionIDUtil;
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
* Created by CodeGenerator on 2019/07/26.
*/
@RestController
@RequestMapping("/ship/info")
public class ShipInfoController {
    @Resource
    private ShipInfoService shipInfoService;
    private ShipUploadService shipUploadService;
    private ShipInfo shipInfo;

    @PostMapping("/add")
    public Result add(ShipInfo shipInfo) {
        shipInfoService.save(shipInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        shipInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ShipInfo shipInfo) {
        shipInfoService.update(shipInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ShipInfo shipInfo = shipInfoService.findById(id);
        return ResultGenerator.genSuccessResult(shipInfo);
    }

/*    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ShipInfo> list = shipInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }*/

    /**
     * 查询所有船（包括静态船）
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ShipInfo> list = shipInfoService.findAllIncludeStaticShip();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);

    }

    /**
     * 查询附近多少公里的位置，
     * @param radii  范围 几公里，单位公里
     * @param longitude   自身经度
     * @param latitude   自身纬度
     * @return
     */

    @PostMapping("/nearbyShip")
    public Result getVicinity(@RequestParam(defaultValue = "2") double radii, double longitude, double latitude) {
        double r = 6371;//地球半径千米
        double dis = radii;
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;

        List<ShipInfo> list = shipInfoService.getVicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat));
        return ResultGenerator.genSuccessResult(list);
    }


/*    @PostMapping("/updateShipInfoBetweenId")
    public Result updateShipInfoBetweenId(@RequestParam Integer id1, @RequestParam Integer id2) {
        shipInfoService.updateShipInfoBetweenId(id1, id2);
        return ResultGenerator.genSuccessResult();
    }*/

    @PostMapping("/nearbyShipOld")
    public Result nearbyShip(@RequestParam BigDecimal longitude, @RequestParam BigDecimal latitude){
        Coordinate coordinate =new Coordinate(longitude,latitude);
        String channelDivisionID = ChannelDivisionIDUtil.getChannelDivisionID(coordinate);
        System.out.println("%%%%%%%%% 当前船舶 航道划分："+channelDivisionID+"%%%%%%%%%");

        String[] nearbyChannelDivisionID = ChannelDivisionIDUtil.getNearbyChannelDivisionID(channelDivisionID);



        for (int i=0;i<nearbyChannelDivisionID.length;i++){
            System.out.println("$$$$$$$$$$$$$$$$$$$--ShipInfo 周围航道划分【数组】："+nearbyChannelDivisionID[i]);}

        Condition condition = new Condition(ShipInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[0]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[1]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[2]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[3]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[4]);


        List<ShipInfo> list = shipInfoService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }


/*    @PostMapping("/nearbyShip")
    public Result nearbyShip(@RequestParam Integer MMSI){

        String channelDivisionID = MapperUtil.getShipUploadCDID(MMSI);
        System.out.println("%%%%%%%%% ShipUpload当前船舶 航道划分："+channelDivisionID+"%%%%%%%%%");

        String[] nearbyChannelDivisionID = ChannelDivisionIDUtil.getNearbyChannelDivisionID(channelDivisionID);



        for (int i=0;i<nearbyChannelDivisionID.length;i++){
            System.out.println("$$$$$$$$$$$$$$$$$$$--ShipInfo 周围航道划分【数组】："+nearbyChannelDivisionID[i]);}

        Condition condition = new Condition(ShipInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[0]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[1]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[2]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[3]);
        criteria.orEqualTo("channelDivisionId", nearbyChannelDivisionID[4]);

        List<ShipInfo> list = shipInfoService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }*/

}

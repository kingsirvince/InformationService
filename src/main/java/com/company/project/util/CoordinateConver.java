package com.company.project.util;

import com.company.project.model.ShipInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 * 坐标轴转换
 * WGS84转百度BD坐标
 * 2020.05.27
 */
@Component
public class CoordinateConver {
    public static final String BAIDU_LBS_TYPE = "bd09ll";

    public static double pi = 3.1415926535897932384626;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;


    public static List<ShipInfo> gps84_To_Bd09(List<ShipInfo> shipInfoList) {
        for (ShipInfo item : shipInfoList) {
            //84 to 火星坐标系 (GCJ-02)
            double lon = Double.parseDouble(item.getShipLongitude().toString());
            double lat = Double.parseDouble(item.getShipLatitude().toString());

            /*            if (outOfChina(lat, lon)) {
                return null;
            }*/
            double dLat = transformLat(lon - 105.0, lat - 35.0);
            double dLon = transformLon(lon - 105.0, lat - 35.0);
            double radLat = lat / 180.0 * pi;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
            double x = lon + dLon;
            double y = lat + dLat;

            //火星坐标系 (GCJ-02) to 百度坐标系 (BD-09)
            double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
            double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
            double bd_lon = z * Math.cos(theta) + 0.0065;
            double bd_lat = z * Math.sin(theta) + 0.006;
            //            保留6为小数

            BigDecimal blon = new BigDecimal(bd_lon);
            blon = blon.setScale(6, RoundingMode.HALF_UP);
            BigDecimal blat = new BigDecimal(bd_lat);
            blat = blat.setScale(6, RoundingMode.HALF_UP);


            item.setShipLongitude(blon);
            item.setShipLatitude(blat);
        }
        return shipInfoList;
    }

   /* public static TAcqData gps84_To_Bd09(TAcqData tAcqData) {
        double lon = tAcqData.getLongitude(), lat = tAcqData.getLatitude();
        //经纬度超出中国范围
        *//*            if (outOfChina(lat, lon)) {
                return null;
            }*//*
         *//*
                GPS84 to 火星坐标系 (GCJ-02)
            *//*
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double x = lon + dLon;
        double y = lat + dLat;

        //火星坐标系 (GCJ-02) to 百度坐标系 (BD-09)
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        //            保留7为小数

        BigDecimal blon = new BigDecimal(bd_lon);
        bd_lon = blon.setScale(7, RoundingMode.HALF_UP).doubleValue();
        BigDecimal blat = new BigDecimal(bd_lat);
        bd_lat = blat.setScale(7, RoundingMode.HALF_UP).doubleValue();
        tAcqData.setLongitude(bd_lon);
        tAcqData.setLatitude(bd_lat);

        return tAcqData;
    }

    *//**
     * gps84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     *
     * @return
     *//*
    public static List<TAcqData> gps84_To_Gcj02(List<TAcqData> tAcqDatas) {
        for (TAcqData item : tAcqDatas) {
            *//*
                GPS84 to 火星坐标系 (GCJ-02)
            *//*
            double lon = item.getLongitude(), lat = item.getLatitude();

            *//**
             *if (outOfChina(lat, lon)) {
             return null;
             }*//*
            double dLat = transformLat(lon - 105.0, lat - 35.0);
            double dLon = transformLon(lon - 105.0, lat - 35.0);
            double radLat = lat / 180.0 * pi;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
            double mgLat = lat + dLat;
            double mgLon = lon + dLon;
            item.setLongitude(mgLon);
            item.setLatitude(mgLat);
        }
        return tAcqDatas;
    }

    public static List<TAcqData> gcj02_To_Bd09(List<TAcqData> tAcqDatas) {
        for (TAcqData item : tAcqDatas) {
            //火星坐标系 (GCJ-02) to 百度坐标系 (BD-09)
            double x = item.getLongitude(), y = item.getLatitude();
            double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
            double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
            double bd_lon = z * Math.cos(theta) + 0.0065;
            double bd_lat = z * Math.sin(theta) + 0.006;
            //保留7为小数
            BigDecimal blon = new BigDecimal(bd_lon);
            bd_lon = blon.setScale(7, RoundingMode.HALF_UP).doubleValue();
            BigDecimal blat = new BigDecimal(bd_lat);
            bd_lat = blat.setScale(7, RoundingMode.HALF_UP).doubleValue();
            item.setLongitude(bd_lon);
            item.setLatitude(bd_lat);
        }
        return tAcqDatas;
    }*/

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }
}

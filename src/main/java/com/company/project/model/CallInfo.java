package com.company.project.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "call_info")
public class CallInfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Call_name")
    private String callName;

    @Column(name = "Call_class")
    private String callClass;

    @Column(name = "Call_grade")
    private String callGrade;

    @Column(name = "Call_unhandled")
    private String callUnhandled;

    @Column(name = "Call_longitude")
    private BigDecimal callLongitude;

    @Column(name = "Call_latitude")
    private BigDecimal callLatitude;

    @Column(name = "Call_ship")
    private String callShip;

    @Column(name = "Call_mmsi")
    private Integer callMmsi;

    @Column(name = "Call_content")
    private String callContent;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "Datetime")
    private Date datetime;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Call_name
     */
    public String getCallName() {
        return callName;
    }

    /**
     * @param callName
     */
    public void setCallName(String callName) {
        this.callName = callName;
    }

    /**
     * @return Call_class
     */
    public String getCallClass() {
        return callClass;
    }

    /**
     * @param callClass
     */
    public void setCallClass(String callClass) {
        this.callClass = callClass;
    }

    /**
     * @return Call_grade
     */
    public String getCallGrade() {
        return callGrade;
    }

    /**
     * @param callGrade
     */
    public void setCallGrade(String callGrade) {
        this.callGrade = callGrade;
    }

    public String getCallUnhandled() {
        return callUnhandled;
    }

    public void setCallUnhandled(String callUnhandled) {
        this.callUnhandled = callUnhandled;
    }

    /**
     * @return Call_longitude
     */
    public BigDecimal getCallLongitude() {
        return callLongitude;
    }

    /**
     * @param callLongitude
     */
    public void setCallLongitude(BigDecimal callLongitude) {
        this.callLongitude = callLongitude;
    }

    /**
     * @return Call_latitude
     */
    public BigDecimal getCallLatitude() {
        return callLatitude;
    }

    /**
     * @param callLatitude
     */
    public void setCallLatitude(BigDecimal callLatitude) {
        this.callLatitude = callLatitude;
    }

    public String getCallShip() {
        return callShip;
    }

    public void setCallShip(String callShip) {
        this.callShip = callShip;
    }

    /**
     * @return Call_mmsi
     */
    public Integer getCallMmsi() {
        return callMmsi;
    }

    /**
     * @param callMmsi
     */
    public void setCallMmsi(Integer callMmsi) {
        this.callMmsi = callMmsi;
    }

    /**
     * @return Call_content
     */
    public String getCallContent() {
        return callContent;
    }

    /**
     * @param callContent
     */
    public void setCallContent(String callContent) {
        this.callContent = callContent;
    }

    /**
     * @return Datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
package com.qingmang.moudle.entity;

import java.io.Serializable;

/**
 * Created by xiejingbao on 2018/4/10.
 */

public class CustomerInfo implements Serializable{

    /**
     * id : 1
     * createTime : 1516032000000
     * modifyTime : 1516032000000
     * version : 1
     * name : 111
     * avatar : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/picture/2017/12/27/1514377476836338.jpg
     * phone : 15190565334
     * pwd : null
     * phoneModel : kupai8675
     * province : 江苏
     * city : 苏州
     * address : null
     * token : d19fe3f49b8f4a9a8829acf80f5c5407
     * ip : 127.0.0.1
     * state : active
     * number : 1
     * platNum : 4
     * merchantId : 32083001127239
     * auditFlag : 1
     * merchantFlag : 1
     * workstate : 在职
     * income : 20000以上
     * unitname : 苏州海云网络科技有限公司
     * realname : 李伟才
     * email : 992007738@qq.com
     * idCard : 412000000000000000
     */

    private int id;
    private long createTime;
    private long modifyTime;
    private int version;
    private String name;
    private String avatar;
    private String phone;
    private Object pwd;
    private String phoneModel;
    private String province;
    private String city;
    private Object address;
    private String token;
    private String ip;
    private String state;
    private int number;
    private int platNum;
    private String merchantId;
    private String auditFlag;
    private String merchantFlag;
    private String workstate;
    private String income;
    private String unitname;
    private String realname;
    private String email;
    private String idCard;
    private int creditnum;

    private double loanbalance;
    private double smallchange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPlatNum() {
        return platNum;
    }

    public void setPlatNum(int platNum) {
        this.platNum = platNum;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public String getMerchantFlag() {
        return merchantFlag;
    }

    public void setMerchantFlag(String merchantFlag) {
        this.merchantFlag = merchantFlag;
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getCreditnum() {
        return creditnum;
    }

    public void setCreditnum(int creditnum) {
        this.creditnum = creditnum;
    }

    public double getLoanbalance() {
        return loanbalance;
    }

    public void setLoanbalance(double loanbalance) {
        this.loanbalance = loanbalance;
    }

    public double getSmallchange() {
        return smallchange;
    }

    public void setSmallchange(double smallchange) {
        this.smallchange = smallchange;
    }
}

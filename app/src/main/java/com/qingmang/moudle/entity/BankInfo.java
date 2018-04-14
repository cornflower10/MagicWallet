package com.qingmang.moudle.entity;

/**
 * Created by xiejingbao on 2018/4/14.
 */

public class BankInfo {

    /**
     * id : 1
     * createTime : 1516254303000
     * modifyTime : 1516254303000
     * version : 1
     * logo : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/picture/2017/09/20/1505872469118782.jpg
     * name : wzl_bank
     * introduct : introductaaaaaaaaaaaaaaaaaaaaaaaaaaa
     * active : activeaaaaaaaaaaa
     * passUpper : 100
     * passLower : 80
     * batchUpper : 1
     * batchLower : 3
     * scopeUpper : 100000
     * scopeLower : 1000
     * app : app:www.baidu.com.app
     * appLogo : null
     * hotLine : 15190565321
     * state : active
     * number : 3
     */

    private int id;
    private long createTime;
    private long modifyTime;
    private int version;
    private String logo;
    private String name;
    private String introduct;
    private String active;
    private int passUpper;
    private int passLower;
    private int batchUpper;
    private int batchLower;
    private int scopeUpper;
    private int scopeLower;
    private String app;
    private String appLogo;
    private String hotLine;
    private String state;
    private int number;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getPassUpper() {
        return passUpper;
    }

    public void setPassUpper(int passUpper) {
        this.passUpper = passUpper;
    }

    public int getPassLower() {
        return passLower;
    }

    public void setPassLower(int passLower) {
        this.passLower = passLower;
    }

    public int getBatchUpper() {
        return batchUpper;
    }

    public void setBatchUpper(int batchUpper) {
        this.batchUpper = batchUpper;
    }

    public int getBatchLower() {
        return batchLower;
    }

    public void setBatchLower(int batchLower) {
        this.batchLower = batchLower;
    }

    public int getScopeUpper() {
        return scopeUpper;
    }

    public void setScopeUpper(int scopeUpper) {
        this.scopeUpper = scopeUpper;
    }

    public int getScopeLower() {
        return scopeLower;
    }

    public void setScopeLower(int scopeLower) {
        this.scopeLower = scopeLower;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
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
}

package com.qingmang.moudle.entity;

/**
 * Created by xiejingbao on 2018/4/12.
 */

public class CreditCard {

    /**
     * id : 1
     * createTime : 1516259946000
     * modifyTime : 1516259946000
     * version : 1
     * bankId : 1
     * bankName : wzl_bank
     * name : wzl_card11
     * logo : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/picture/2017/09/20/1505872469118782.jpg
     * introduct : introductaaa
     * level : ordinary
     * currency : rmb
     * tags : 百元刷卡金,500元刷卡金
     * organization : 银联
     * freePeriod : 最短20天，最长50天
     * specification : 每消费5元累计1集分宝，100个集分宝价值￥1元
     * qualification : 申请资格aaa
     * annualFee : 80/年，首年免费，刷卡满6次及以上滚动免次年费
     * proportion : 最高取现比例50%
     * withdrawal : 取现手续费为取现金额的2.5%，最低大于取现费10元
     * repayment : 最低还款金额为本月账单的10%
     * userCond : 2018年6月26日首年免费，刷卡满6次及以上阿萨德第三方滚动免次年年费
     * amount : 2018年6月26日首年免费，刷卡满6次及以上阿萨德第三方滚动免次年年费
     * amountLevel : 3
     * batch : 容易批卡，不妨申请试试
     * batchLevel : 3
     * difficult : 填表简单，容易填写
     * difficultLevel : 3
     * link : aaawwww.baidu.comaaaa
     * state : active
     * number : 13
     */

    private int id;
    private long createTime;
    private long modifyTime;
    private int version;
    private int bankId;
    private String bankName;
    private String name;
    private String logo;
    private String introduct;
    private String level;
    private String currency;
    private String tags;
    private String organization;
    private String freePeriod;
    private String specification;
    private String qualification;
    private String annualFee;
    private String proportion;
    private String withdrawal;
    private String repayment;
    private String userCond;
    private String amount;
    private int amountLevel;
    private String batch;
    private int batchLevel;
    private String difficult;
    private int difficultLevel;
    private String link;
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFreePeriod() {
        return freePeriod;
    }

    public void setFreePeriod(String freePeriod) {
        this.freePeriod = freePeriod;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(String annualFee) {
        this.annualFee = annualFee;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }

    public String getUserCond() {
        return userCond;
    }

    public void setUserCond(String userCond) {
        this.userCond = userCond;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getAmountLevel() {
        return amountLevel;
    }

    public void setAmountLevel(int amountLevel) {
        this.amountLevel = amountLevel;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getBatchLevel() {
        return batchLevel;
    }

    public void setBatchLevel(int batchLevel) {
        this.batchLevel = batchLevel;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(int difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

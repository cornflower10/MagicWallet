package com.qingmang.moudle.entity;

/**
 * Created by xiejingbao on 2018/4/14.
 */

public class BankCard {

    /**
     * id : 3
     * memberId : 1
     * bankCode : 302100011000
     * bankName : 中信银行
     * creditCode : 5149060010610355
     * ownerName : 李伟才
     * creditLine : null
     * cvn2 : 965
     * mobile : 18688011888
     * valiDate : 2208
     * cashNum : null
     * repayDate : null
     * fontimg : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/card/2018/03/281522230351572225.jpg
     * backimg : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/card/2018/03/281522230351972284.jpg
     * codeimg : http://livehaiyunx.oss-cn-shanghai.aliyuncs.com/picture/loan/bank/CITIC.png
     */

    private int id;
    private int memberId;
    private String bankCode;
    private String bankName;
    private String creditCode;
    private String ownerName;
    private Object creditLine;
    private String cvn2;
    private String mobile;
    private String valiDate;
    private Object cashNum;
    private String repayDate;
    private String fontimg;
    private String backimg;
    private String codeimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Object getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Object creditLine) {
        this.creditLine = creditLine;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getValiDate() {
        return valiDate;
    }

    public void setValiDate(String valiDate) {
        this.valiDate = valiDate;
    }

    public Object getCashNum() {
        return cashNum;
    }

    public void setCashNum(Object cashNum) {
        this.cashNum = cashNum;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getFontimg() {
        return fontimg;
    }

    public void setFontimg(String fontimg) {
        this.fontimg = fontimg;
    }

    public String getBackimg() {
        return backimg;
    }

    public void setBackimg(String backimg) {
        this.backimg = backimg;
    }

    public String getCodeimg() {
        return codeimg;
    }

    public void setCodeimg(String codeimg) {
        this.codeimg = codeimg;
    }
}

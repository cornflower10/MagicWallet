package com.qingmang.moudle.entity;

import java.util.List;

/**
 * Created by xiejingbao on 2018/5/14.
 */

public class CreditCardOrder {

    /**
     * pageNumber : 1
     * pageSize : 20
     * totalCount : 4
     * pageCount : 1
     * content : [{"id":1,"createTime":1516291200000,"modifyTime":1525276800000,"version":3,"memberId":1,"memberName":"111","idCard":"352624197803241017","bankId":null,"bankName":"平安银行","cardId":1,"cardName":"平安标准卡","state":"active","ip":"127.0.0.1","province":"江苏","city":"苏州","phone":"15190565321","phoneModel":"cupai8675","cardnum":"352624197803241017","amount":"20000.00","orderno":"C201801190000001"},{"id":2,"createTime":1516291200000,"modifyTime":1525276800000,"version":3,"memberId":1,"memberName":"111","idCard":"352624197803241017","bankId":null,"bankName":"平安银行","cardId":1,"cardName":"平安标准卡","state":"active","ip":"127.0.0.1","province":"江苏","city":"苏州","phone":"15190565321","phoneModel":"cupai8675","cardnum":"352624197803241017","amount":"20000.00","orderno":"C201801190000002"},{"id":3,"createTime":1516291200000,"modifyTime":1525276800000,"version":3,"memberId":1,"memberName":"111","idCard":"352624197803241017","bankId":null,"bankName":"平安银行","cardId":1,"cardName":"平安标准卡","state":"active","ip":"127.0.0.1","province":"江苏","city":"苏州","phone":"15190565321","phoneModel":"cupai8675","cardnum":"352624197803241017","amount":"20000.00","orderno":"C201801190000003"},{"id":4,"createTime":1516291200000,"modifyTime":1525276800000,"version":3,"memberId":1,"memberName":"111","idCard":"352624197803241017","bankId":null,"bankName":"平安银行","cardId":1,"cardName":"平安标准卡","state":"active","ip":"127.0.0.1","province":"江苏","city":"苏州","phone":"15190565321","phoneModel":"cupai8675","cardnum":"352624197803241017","amount":"20000.00","orderno":"C201801190000004"}]
     */

    private int pageNumber;
    private int pageSize;
    private int totalCount;
    private int pageCount;
    private List<ContentBean> content;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 1
         * createTime : 1516291200000
         * modifyTime : 1525276800000
         * version : 3
         * memberId : 1
         * memberName : 111
         * idCard : 352624197803241017
         * bankId : null
         * bankName : 平安银行
         * cardId : 1
         * cardName : 平安标准卡
         * state : active
         * ip : 127.0.0.1
         * province : 江苏
         * city : 苏州
         * phone : 15190565321
         * phoneModel : cupai8675
         * cardnum : 352624197803241017
         * amount : 20000.00
         * orderno : C201801190000001
         */

        private int id;
        private long createTime;
        private long modifyTime;
        private int version;
        private int memberId;
        private String memberName;
        private String idCard;
        private Object bankId;
        private String bankName;
        private int cardId;
        private String cardName;
        private String state;
        private String ip;
        private String province;
        private String city;
        private String phone;
        private String phoneModel;
        private String cardnum;
        private String amount;
        private String orderno;

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

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Object getBankId() {
            return bankId;
        }

        public void setBankId(Object bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneModel() {
            return phoneModel;
        }

        public void setPhoneModel(String phoneModel) {
            this.phoneModel = phoneModel;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }
    }
}

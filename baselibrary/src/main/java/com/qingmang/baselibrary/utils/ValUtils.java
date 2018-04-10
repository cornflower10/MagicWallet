package com.qingmang.baselibrary.utils;

import android.text.TextUtils;

/**
 * Created by xiejingbao on 2018/3/26.
 */

public class ValUtils {

    /**
     * 验证手机格式
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0135678]|18[0-9]|14[579]|852|853)[0-9]{8}$";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean isMailbox(String email) {
        String telRegex = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        if (TextUtils.isEmpty(email)) return false;
        else return email.matches(telRegex);
    }

    /**
     * 验证手机验证码是否为6位数字
     * @param mobilesCode
     * @return
     */
    public static boolean isMobileCode(String mobilesCode) {
        String telRegex = "\\d{6}";
        if (TextUtils.isEmpty(mobilesCode)) return false;
        else return mobilesCode.matches(telRegex);
    }

    /**
     * 验证图形验证码是否为4位数字
     * @param imageCode
     * @return
     */
    public static boolean isImageCode(String imageCode) {
        if (TextUtils.isEmpty(imageCode)) return false;
        else return imageCode.length() >= 4;
    }

    /**
     * 验证登录密码格式是否是6-16位，同时包含字母与数字
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        String telRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        if (TextUtils.isEmpty(password)) return false;
        else return password.matches(telRegex);
    }

    /**
     * 验证银行卡号格式是否正确
     * @param bankNumber
     * @return
     */
    public static boolean isBankCard(String bankNumber) {
        String telRegex = "^[0-9]{16,19}$";
        if (TextUtils.isEmpty(bankNumber)) return false;
        else return bankNumber.matches(telRegex);
    }

    /**
     * 验证身份证号码
     * @param idNo
     * @return
     */
    public static boolean isIdNo(String idNo) {
        String telRegex = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        if (TextUtils.isEmpty(idNo)) return false;
        else return idNo.matches(telRegex);
    }

}

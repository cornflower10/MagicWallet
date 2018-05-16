package com.qingmang.baselibrary.utils;

import java.text.DecimalFormat;

/**
 * Created by xiejingbao on 2018/3/26.
 */

public class AmountUtils {

    public static String amountFormat (double amount) {
        if(amount<= 0){
            return "0.00";
        }
        return amountFormat (amount,"###,###.00");

    }

    public static String amountFormat (double amount, String format) {
        DecimalFormat decimalFormat = new DecimalFormat();
        if (amount < 1) {
            decimalFormat.applyPattern("0.00");
        } else {
            decimalFormat.applyPattern(format);
        }
        return decimalFormat.format(amount);
    }
}

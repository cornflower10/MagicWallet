package com.qingmang.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiejingbao on 2018/3/26.
 */

public class DateUtils {
    /**
     * 毫秒数转换为指定格式的日期
     * @param
     * @param millSec
     * @return
     */
    public static String transferLongToDate(Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date(millSec);
        return sdf.format(date);
    }

}

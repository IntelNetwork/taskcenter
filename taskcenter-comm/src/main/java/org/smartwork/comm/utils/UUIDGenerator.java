package org.smartwork.comm.utils;

import java.util.Random;

/**
 * @ClassName
 * @Description 随机生成指定位数的UUID
 * @Author xfx
 * @Date 2019/12/3 14:59
 * @Version 1.0
 **/
public class UUIDGenerator {

    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 由大小写字母、数字组成的随机字符串
     * @param length
     * @return
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }
}

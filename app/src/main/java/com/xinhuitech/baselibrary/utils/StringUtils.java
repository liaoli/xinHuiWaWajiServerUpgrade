package com.xinhuitech.baselibrary.utils;

import android.text.TextUtils;

/**
 * @author 刘琛慧
 *         date 2016/6/21.
 */
public class StringUtils {

    public static String formatPhoneNumber(String phoneNo, int start, int replaceLength) {
        if (TextUtils.isEmpty(phoneNo) || phoneNo.length() < (start + replaceLength)) {
            return phoneNo;
        }

        StringBuilder stringBuilder = new StringBuilder(phoneNo);
        return stringBuilder.replace(start, start + replaceLength, "****").toString();
    }


    /**
     * 半角转换为全角, 解决textview自动换行导致排版错乱
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}

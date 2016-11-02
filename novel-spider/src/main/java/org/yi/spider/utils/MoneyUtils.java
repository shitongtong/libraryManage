//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import org.apache.commons.lang.math.NumberUtils;
import org.yi.spider.utils.StringUtils;

public class MoneyUtils {
    private static final String[] NUMBERS = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] IUNIT = new String[]{"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};
    private static final String[] DUNIT = new String[]{"角", "分", "厘"};

    public MoneyUtils() {
    }

    public static String toChineseCapital(String numMoney) throws Exception {
        if(StringUtils.isBlank(numMoney)) {
            throw new Exception("当前传入的金额参数为[" + numMoney + "]，为空或空字符串或空格字符串，请检查！");
        } else if(!NumberUtils.isNumber(numMoney)) {
            throw new Exception("当前传入的金额参数为[" + numMoney + "]，不为金额格式，请检查！");
        } else {
            numMoney = numMoney.trim().replaceAll(",", "");
            String integerStr;
            String decimalStr;
            if(numMoney.indexOf(".") > 0) {
                integerStr = numMoney.substring(0, numMoney.indexOf("."));
                decimalStr = numMoney.substring(numMoney.indexOf(".") + 1);
            } else if(numMoney.indexOf(".") == 0) {
                integerStr = "";
                decimalStr = numMoney.substring(1);
            } else {
                integerStr = numMoney;
                decimalStr = "";
            }

            if(!integerStr.equals("")) {
                integerStr = Long.toString(Long.parseLong(integerStr));
                if(integerStr.equals("0")) {
                    integerStr = "";
                }
            }

            if(integerStr.length() > IUNIT.length) {
                return numMoney;
            } else {
                int[] integers = toArray(integerStr);
                boolean isMust5 = isMust5(integerStr);
                int[] decimals = toArray(decimalStr);
                return getChineseInteger(integers, isMust5) + getChineseDecimal(decimals);
            }
        }
    }

    public static String toChineseCapital(double numMoney) throws Exception {
        return toChineseCapital(String.valueOf(numMoney));
    }

    private static int[] toArray(String number) {
        int[] array = new int[number.length()];

        for(int i = 0; i < number.length(); ++i) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }

        return array;
    }

    private static String getChineseInteger(int[] integers, boolean isMust5) {
        StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;

        for(int i = 0; i < length; ++i) {
            String key = "";
            if(integers[i] == 0) {
                if(length - i == 13) {
                    key = IUNIT[4];
                } else if(length - i == 9) {
                    key = IUNIT[8];
                } else if(length - i == 5 && isMust5) {
                    key = IUNIT[4];
                } else if(length - i == 1) {
                    key = IUNIT[0];
                }

                if(length - i > 1 && integers[i + 1] != 0) {
                    key = key + NUMBERS[0];
                }
            }

            chineseInteger.append(integers[i] == 0?key:NUMBERS[integers[i]] + IUNIT[length - i - 1]);
        }

        return chineseInteger.toString();
    }

    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");

        for(int i = 0; i < decimals.length && i != 3; ++i) {
            chineseDecimal.append(decimals[i] == 0?"":NUMBERS[decimals[i]] + DUNIT[i]);
        }

        return chineseDecimal.toString();
    }

    private static boolean isMust5(String integerStr) {
        int length = integerStr.length();
        if(length > 4) {
            String subInteger = "";
            if(length > 8) {
                subInteger = integerStr.substring(length - 8, length - 4);
            } else {
                subInteger = integerStr.substring(0, length - 4);
            }

            return Integer.parseInt(subInteger) > 0;
        } else {
            return false;
        }
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import java.util.Arrays;

public class SecurityCode {
    public SecurityCode() {
    }

    public static String getSecurityCode() {
        return getSecurityCode(4, SecurityCode.SecurityCodeLevel.Medium, false);
    }

    public static String getSecurityCode(int length, SecurityCode.SecurityCodeLevel level, boolean isCanRepeat) {
        char[] codes = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        if(level == SecurityCode.SecurityCodeLevel.Simple) {
            codes = Arrays.copyOfRange(codes, 0, 9);
        } else if(level == SecurityCode.SecurityCodeLevel.Medium) {
            codes = Arrays.copyOfRange(codes, 0, 33);
        }

        int n = codes.length;
        if(length > n && !isCanRepeat) {
            throw new RuntimeException(String.format("调用SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", new Object[]{Integer.valueOf(length), level, Boolean.valueOf(isCanRepeat), Integer.valueOf(n)}));
        } else {
            char[] result = new char[length];
            int i;
            int r;
            if(isCanRepeat) {
                for(i = 0; i < result.length; ++i) {
                    r = (int)(Math.random() * (double)n);
                    result[i] = codes[r];
                }
            } else {
                for(i = 0; i < result.length; ++i) {
                    r = (int)(Math.random() * (double)n);
                    result[i] = codes[r];
                    codes[r] = codes[n - 1];
                    --n;
                }
            }

            return String.valueOf(result);
        }
    }

    public static enum SecurityCodeLevel {
        Simple,
        Medium,
        Hard;

        private SecurityCodeLevel() {
        }
    }
}

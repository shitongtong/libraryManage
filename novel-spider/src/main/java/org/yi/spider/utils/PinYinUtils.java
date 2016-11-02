//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

public class PinYinUtils {
    public PinYinUtils() {
    }

    @Test
    public void test() {
        System.out.println(converterToFirstSpell("重"));
        System.out.println(converterToSpell("重当参"));
        System.out.println(getPinYin("112长沙市长"));
        System.out.println(getFirst1Spell("123地方asdc"));
        System.out.println(getFullSpell("长沙市长"));
    }

    public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for(int e = 0; e < input.length; ++e) {
                if(Character.toString(input[e]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[e], format);
                    output = output + temp[0];
                } else {
                    output = output + input[e];
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return output;
    }

    /** @deprecated */
    public static String getFirst1Spell(String chinese) {
        String spells = converterToFirstSpell(chinese);
        return spells.substring(0, 1);
    }

    /** @deprecated */
    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        for(int i = 0; i < arr.length; ++i) {
            if(arr[i] > 128) {
                try {
                    String[] e = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if(e != null) {
                        pybf.append(e[0].charAt(0));
                    } else {
                        pybf.append(arr[i]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            }
        }

        return pybf.toString().replaceAll("\\W", "").trim();
    }

    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            for(int e = 0; e < arr.length; ++e) {
                if(arr[e] > 128) {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[e], defaultFormat)[0]);
                } else {
                    pybf.append(arr[e]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination var5) {
            var5.printStackTrace();
        }

        return pybf.toString();
    }

    /** @deprecated */
    public static String converterToFirstSpell(String chines) {
        StringBuffer pinyinName = new StringBuffer();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < nameChar.length; ++i) {
            if(nameChar[i] > 128) {
                try {
                    String[] e = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    if(e != null) {
                        for(int j = 0; j < e.length; ++j) {
                            pinyinName.append(e[j].charAt(0));
                            if(j != e.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination var7) {
                    var7.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }

            pinyinName.append(" ");
        }

        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    public static String converterToSpell(String chines) {
        StringBuffer pinyinName = new StringBuffer();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < nameChar.length; ++i) {
            if(nameChar[i] > 128) {
                try {
                    String[] e = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    if(e != null) {
                        for(int j = 0; j < e.length; ++j) {
                            pinyinName.append(e[j]);
                            if(j != e.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination var7) {
                    var7.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }

            pinyinName.append(" ");
        }

        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }

    private static List<Map<String, Integer>> discountTheChinese(String theStr) {
        ArrayList mapList = new ArrayList();
        Hashtable onlyOne = null;
        String[] firsts = theStr.split(" ");
        String[] var7 = firsts;
        int var6 = firsts.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String str = var7[var5];
            onlyOne = new Hashtable();
            String[] china = str.split(",");
            String[] var12 = china;
            int var11 = china.length;

            for(int var10 = 0; var10 < var11; ++var10) {
                String s = var12[var10];
                Integer count = (Integer)onlyOne.get(s);
                if(count == null) {
                    onlyOne.put(s, new Integer(1));
                } else {
                    onlyOne.remove(s);
                    count = Integer.valueOf(count.intValue() + 1);
                    onlyOne.put(s, count);
                }
            }

            mapList.add(onlyOne);
        }

        return mapList;
    }

    private static String parseTheChineseByObject(List<Map<String, Integer>> list) {
        Hashtable first = null;

        for(int returnStr = 0; returnStr < list.size(); ++returnStr) {
            Hashtable str = new Hashtable();
            String s;
            Iterator var5;
            if(first == null) {
                var5 = ((Map)list.get(returnStr)).keySet().iterator();

                while(var5.hasNext()) {
                    s = (String)var5.next();
                    str.put(s, Integer.valueOf(1));
                }
            } else {
                var5 = first.keySet().iterator();

                while(true) {
                    if(!var5.hasNext()) {
                        if(str != null && str.size() > 0) {
                            first.clear();
                        }
                        break;
                    }

                    s = (String)var5.next();
                    Iterator var7 = ((Map)list.get(returnStr)).keySet().iterator();

                    while(var7.hasNext()) {
                        String str1 = (String)var7.next();
                        String str2 = s + str1;
                        str.put(str2, Integer.valueOf(1));
                    }
                }
            }

            if(str != null && str.size() > 0) {
                first = str;
            }
        }

        String var9 = "";
        String var10;
        if(first != null) {
            for(Iterator var11 = first.keySet().iterator(); var11.hasNext(); var9 = var9 + var10 + ",") {
                var10 = (String)var11.next();
            }
        }

        if(var9.length() > 0) {
            var9 = var9.substring(0, var9.length() - 1);
        }

        return var9;
    }

    public static String getPinyinShouZiMu(String pinyin) {
        char[] var4;
        int var3 = (var4 = pinyin.toCharArray()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            char c = var4[var2];
            if(c >= 97 && c <= 122) {
                return String.valueOf(c);
            }
        }

        return null;
    }
}

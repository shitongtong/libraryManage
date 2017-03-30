package com.stt.java.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author XuYao
 */
public final class ValidateUtil {
	
	/**
	 * 判断一个字符是否是数字
	 * @param content
	 * @return
	 */
	public static boolean isNum(String content){
		
		boolean isNum = content.matches("[0-9]+"); 
		
		return isNum;
	}

    /**
     * 判断邮箱的合法性
     *
     * @param email 邮箱号
     * @return 布尔值
     */
    public static boolean emailVerifier(String email) {
        boolean flag = false;
        //判断邮箱号是否为空
        if (!isNotEmpty(email)) {
            return flag;
        }
        //邮箱的正则表达式 判断邮箱是否合法
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher matcher = pattern.matcher(email);
        flag = matcher.matches();
        return flag;
    }
    
    /**
     * 判断密码的合法性
     *
     * @param email 邮箱号
     * @return 布尔值
     */
    public static boolean passwordVerifier(String password) {
        boolean flag = false;
        //判断密码是否为空是否为空
        if (!isNotEmpty(password)) {
            return flag;
        }
        //密码的正则表达式 判断密码是否合法
        String regStr = "(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$).{8,20}";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(password);
        flag = matcher.matches();
        return flag;
    }

    /**
     * 判断用户姓名的合法性 满足少数名族的姓名
     *
     * @param idName 用户姓名
     * @return boolean {true:姓名合法 flase:姓名不合法}
     */
    public static boolean IdNameValidate(String idName) {
        boolean flag = false;
        //判断姓名是否为空
        if (!isNotEmpty(idName)) {
            return flag;
        }
        //姓名的正则表达式
        Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*");
        Matcher matcher = pattern.matcher(idName);
        flag = matcher.matches();
        return flag;
    }
    
    /**
     * 電話號碼的正則表達式
     * @param phone 电话号码
     * @return {@link Boolean}true:符合；false:不符合
     */
    public static boolean isPhoneNumber(String phone){  
        String regex="^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regex);  
        return p.matcher(phone).matches();  
    }

    /**
     * 金钱的正则表达式 小数点后只能有两位
     *
     * @param money 金钱值
     * @return {@link Boolean} {true:金钱值合法false：金钱值不合法}
     */
    public static boolean moneyValidate(String money) {
        boolean flag = false;
        //判断金钱是否为空
        if (null == money) {
            return flag;
        }
        //金钱的正则表达式
        Pattern pattern = Pattern.compile("^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$");
        Matcher matcher = pattern.matcher(money);
        flag = matcher.matches();
        return flag;
    }

    /**
     * 判断字符串是否为非空
     *
     * @param parameter 参数
     * @return 非空则返回true，空则返回false
     */
    public static boolean isNotEmpty(String parameter) {
        return parameter != null && !parameter.trim().isEmpty();
    }
    
    /**
     * 判断字符串是否为字符串“null”
     *
     * @param parameter 参数
     * @return 不等于'null'则返回true，反之则返回false
     */
    public static boolean isNotStringNull(String parameter) {
        return !(parameter.toLowerCase().equals("null"));
    }
    
    /**
     * 判断字符创是否是数字并且长度是否大于3
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
	    	if(isNotEmpty(str)){
		         if(str.length()>3){
		 	    		return false;	
		 	      }
		     	  for (int i = 0; i < str.length(); i++){
		     	   if (!Character.isDigit(str.charAt(i))){
		     		   return false;
		     	   }
		     	  }
		     	  return true;
	    }else{
	    	 return true;
	    }
   }
 /*  public static void main(String args[]){
	  
	   System.out.println(isPhoneNumber("13512315152"));
   }*/ 
}

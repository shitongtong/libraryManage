package com.model;

public class User {
   String uid;            //用户id
   String user_name;     //用户名
   String password;      //用户密码
   String email;          //用户绑定邮箱
   String phone;          //用户电话
   String IDCard;         //绑定银行卡
   String name;           //真实姓名
   String bank;           //开户银行
   String bank_location;  //开户银行具体信息
   String Qualificate;       //是否完成认证
   String Qualificat_pic; //身份证照片
   String Qualificate_Number;//身份证
   String Qualificate_Name;//身份证名称
   String app_id;            //app id
   String notify_url;         //支付成功回调
   
 
public String getNotify_url() {
	return notify_url;
}
public void setNotify_url(String notify_url) {
	this.notify_url = notify_url;
}
public String getApp_id() {
	return app_id;
}
public void setApp_id(String app_id) {
	this.app_id = app_id;
}
public String getQualificate_Number() {
	return Qualificate_Number;
}
public void setQualificate_Number(String qualificate_Number) {
	Qualificate_Number = qualificate_Number;
}
public String getQualificate_Name() {
	return Qualificate_Name;
}
public void setQualificate_Name(String qualificate_Name) {
	Qualificate_Name = qualificate_Name;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public String getBank_location() {
	return bank_location;
}
public void setBank_location(String bank_location) {
	this.bank_location = bank_location;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getIDCard() {
	return IDCard;
}
public void setIDCard(String iDCard) {
	IDCard = iDCard;
}
public String getQualificate() {
	return Qualificate;
}
public void setQualificate(String qualificate) {
	Qualificate = qualificate;
}
public String getQualificat_pic() {
	return Qualificat_pic;
}
public void setQualificat_pic(String qualificat_pic) {
	Qualificat_pic = qualificat_pic;
}
   
   
   
}

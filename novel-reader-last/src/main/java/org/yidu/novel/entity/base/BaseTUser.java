package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_user"
 */

public abstract class BaseTUser  implements Serializable {

	public static String REF = "TUser";
	public static String PROP_USERNO = "userno";
	public static String PROP_BRANCH = "branch";
	public static String PROP_REGDATE = "regdate";
	public static String PROP_LINENO = "lineno";
	public static String PROP_TYPE = "type";
	public static String PROP_PASSWORD = "password";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_SUBCATEGORY = "subcategory";
	public static String PROP_REALNAME = "realname";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_ACTIVEDFLAG = "activedflag";
	public static String PROP_ALIPAYACOUNT = "alipayacount";
	public static String PROP_QQ = "qq";
	public static String PROP_CATEGORY = "category";
	public static String PROP_MAILTOKEN = "mailtoken";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_EMAIL = "email";
	public static String PROP_LASTLOGIN = "lastlogin";
	public static String PROP_MOBILENO = "mobileno";
	public static String PROP_VOTECOUNT = "votecount";
	public static String PROP_OPENID = "openid";
	public static String PROP_USERNAME = "username";
	public static String PROP_ID = "id";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_BANKNO = "bankno";
	public static String PROP_SEX = "sex";


	// constructors
	public BaseTUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTUser (int userno) {
		this.setUserno(userno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int userno;

	// fields
	private String loginid;
	private String password;
	private String username;
	private String email;
	private java.util.Date regdate;
	private Short sex;
	private String qq;
	private java.util.Date lastlogin;
	private String lineno;
	private Short type;
	private Integer votecount;
	private Boolean deleteflag;
	private String realname;
	private String id;
	private String mobileno;
	private String branch;
	private String bankno;
	private String alipayacount;
	private Integer category;
	private Integer subcategory;
	private Integer modifyuserno;
	private java.util.Date modifytime;
	private String openid;
	private Boolean activedflag;
	private String mailtoken;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="userno"
     */
	public int getUserno () {
		return userno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param userno the new ID
	 */
	public void setUserno (int userno) {
		this.userno = userno;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: loginid
	 */
	public String getLoginid () {
		return loginid;
	}

	/**
	 * Set the value related to the column: loginid
	 * @param loginid the loginid value
	 */
	public void setLoginid (String loginid) {
		this.loginid = loginid;
	}



	/**
	 * Return the value associated with the column: password
	 */
	public String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword (String password) {
		this.password = password;
	}



	/**
	 * Return the value associated with the column: username
	 */
	public String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (String username) {
		this.username = username;
	}



	/**
	 * Return the value associated with the column: email
	 */
	public String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail (String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: regdate
	 */
	public java.util.Date getRegdate () {
		return regdate;
	}

	/**
	 * Set the value related to the column: regdate
	 * @param regdate the regdate value
	 */
	public void setRegdate (java.util.Date regdate) {
		this.regdate = regdate;
	}



	/**
	 * Return the value associated with the column: sex
	 */
	public Short getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: sex
	 * @param sex the sex value
	 */
	public void setSex (Short sex) {
		this.sex = sex;
	}



	/**
	 * Return the value associated with the column: qq
	 */
	public String getQq () {
		return qq;
	}

	/**
	 * Set the value related to the column: qq
	 * @param qq the qq value
	 */
	public void setQq (String qq) {
		this.qq = qq;
	}



	/**
	 * Return the value associated with the column: lastlogin
	 */
	public java.util.Date getLastlogin () {
		return lastlogin;
	}

	/**
	 * Set the value related to the column: lastlogin
	 * @param lastlogin the lastlogin value
	 */
	public void setLastlogin (java.util.Date lastlogin) {
		this.lastlogin = lastlogin;
	}



	/**
	 * Return the value associated with the column: lineno
	 */
	public String getLineno () {
		return lineno;
	}

	/**
	 * Set the value related to the column: lineno
	 * @param lineno the lineno value
	 */
	public void setLineno (String lineno) {
		this.lineno = lineno;
	}



	/**
	 * Return the value associated with the column: type
	 */
	public Short getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType (Short type) {
		this.type = type;
	}



	/**
	 * Return the value associated with the column: votecount
	 */
	public Integer getVotecount () {
		return votecount;
	}

	/**
	 * Set the value related to the column: votecount
	 * @param votecount the votecount value
	 */
	public void setVotecount (Integer votecount) {
		this.votecount = votecount;
	}



	/**
	 * Return the value associated with the column: deleteflag
	 */
	public Boolean isDeleteflag () {
		return deleteflag;
	}

	/**
	 * Set the value related to the column: deleteflag
	 * @param deleteflag the deleteflag value
	 */
	public void setDeleteflag (Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}



	/**
	 * Return the value associated with the column: realname
	 */
	public String getRealname () {
		return realname;
	}

	/**
	 * Set the value related to the column: realname
	 * @param realname the realname value
	 */
	public void setRealname (String realname) {
		this.realname = realname;
	}



	/**
	 * Return the value associated with the column: id
	 */
	public String getId () {
		return id;
	}

	/**
	 * Set the value related to the column: id
	 * @param id the id value
	 */
	public void setId (String id) {
		this.id = id;
	}



	/**
	 * Return the value associated with the column: mobileno
	 */
	public String getMobileno () {
		return mobileno;
	}

	/**
	 * Set the value related to the column: mobileno
	 * @param mobileno the mobileno value
	 */
	public void setMobileno (String mobileno) {
		this.mobileno = mobileno;
	}



	/**
	 * Return the value associated with the column: branch
	 */
	public String getBranch () {
		return branch;
	}

	/**
	 * Set the value related to the column: branch
	 * @param branch the branch value
	 */
	public void setBranch (String branch) {
		this.branch = branch;
	}



	/**
	 * Return the value associated with the column: bankno
	 */
	public String getBankno () {
		return bankno;
	}

	/**
	 * Set the value related to the column: bankno
	 * @param bankno the bankno value
	 */
	public void setBankno (String bankno) {
		this.bankno = bankno;
	}



	/**
	 * Return the value associated with the column: alipayacount
	 */
	public String getAlipayacount () {
		return alipayacount;
	}

	/**
	 * Set the value related to the column: alipayacount
	 * @param alipayacount the alipayacount value
	 */
	public void setAlipayacount (String alipayacount) {
		this.alipayacount = alipayacount;
	}



	/**
	 * Return the value associated with the column: category
	 */
	public Integer getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory (Integer category) {
		this.category = category;
	}



	/**
	 * Return the value associated with the column: subcategory
	 */
	public Integer getSubcategory () {
		return subcategory;
	}

	/**
	 * Set the value related to the column: subcategory
	 * @param subcategory the subcategory value
	 */
	public void setSubcategory (Integer subcategory) {
		this.subcategory = subcategory;
	}



	/**
	 * Return the value associated with the column: modifyuserno
	 */
	public Integer getModifyuserno () {
		return modifyuserno;
	}

	/**
	 * Set the value related to the column: modifyuserno
	 * @param modifyuserno the modifyuserno value
	 */
	public void setModifyuserno (Integer modifyuserno) {
		this.modifyuserno = modifyuserno;
	}



	/**
	 * Return the value associated with the column: modifytime
	 */
	public java.util.Date getModifytime () {
		return modifytime;
	}

	/**
	 * Set the value related to the column: modifytime
	 * @param modifytime the modifytime value
	 */
	public void setModifytime (java.util.Date modifytime) {
		this.modifytime = modifytime;
	}



	/**
	 * Return the value associated with the column: openid
	 */
	public String getOpenid () {
		return openid;
	}

	/**
	 * Set the value related to the column: openid
	 * @param openid the openid value
	 */
	public void setOpenid (String openid) {
		this.openid = openid;
	}



	/**
	 * Return the value associated with the column: activedflag
	 */
	public Boolean isActivedflag () {
		return activedflag;
	}

	/**
	 * Set the value related to the column: activedflag
	 * @param activedflag the activedflag value
	 */
	public void setActivedflag (Boolean activedflag) {
		this.activedflag = activedflag;
	}



	/**
	 * Return the value associated with the column: mailtoken
	 */
	public String getMailtoken () {
		return mailtoken;
	}

	/**
	 * Set the value related to the column: mailtoken
	 * @param mailtoken the mailtoken value
	 */
	public void setMailtoken (String mailtoken) {
		this.mailtoken = mailtoken;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TUser)) return false;
		else {
			org.yidu.novel.entity.TUser tUser = (org.yidu.novel.entity.TUser) obj;
			return (this.getUserno() == tUser.getUserno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getUserno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
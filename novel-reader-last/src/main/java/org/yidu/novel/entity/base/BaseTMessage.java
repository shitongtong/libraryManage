package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_message table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_message"
 */

public abstract class BaseTMessage  implements Serializable {

	public static String REF = "TMessage";
	public static String PROP_USERNO = "userno";
	public static String PROP_CATEGORY = "category";
	public static String PROP_TOLOGINID = "tologinid";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_ISREAD = "isread";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_CONTENT = "content";
	public static String PROP_TOUSERNO = "touserno";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_TITLE = "title";
	public static String PROP_MESSAGENO = "messageno";


	// constructors
	public BaseTMessage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTMessage (int messageno) {
		this.setMessageno(messageno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int messageno;

	// fields
	private Integer userno;
	private String loginid;
	private Integer touserno;
	private String tologinid;
	private String title;
	private String content;
	private Short category;
	private Boolean isread;
	private java.util.Date postdate;
	private Boolean deleteflag;
	private Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="messageno"
     */
	public int getMessageno () {
		return messageno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param messageno the new ID
	 */
	public void setMessageno (int messageno) {
		this.messageno = messageno;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: userno
	 */
	public Integer getUserno () {
		return userno;
	}

	/**
	 * Set the value related to the column: userno
	 * @param userno the userno value
	 */
	public void setUserno (Integer userno) {
		this.userno = userno;
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
	 * Return the value associated with the column: touserno
	 */
	public Integer getTouserno () {
		return touserno;
	}

	/**
	 * Set the value related to the column: touserno
	 * @param touserno the touserno value
	 */
	public void setTouserno (Integer touserno) {
		this.touserno = touserno;
	}



	/**
	 * Return the value associated with the column: tologinid
	 */
	public String getTologinid () {
		return tologinid;
	}

	/**
	 * Set the value related to the column: tologinid
	 * @param tologinid the tologinid value
	 */
	public void setTologinid (String tologinid) {
		this.tologinid = tologinid;
	}



	/**
	 * Return the value associated with the column: title
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (String title) {
		this.title = title;
	}



	/**
	 * Return the value associated with the column: content
	 */
	public String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (String content) {
		this.content = content;
	}



	/**
	 * Return the value associated with the column: category
	 */
	public Short getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory (Short category) {
		this.category = category;
	}



	/**
	 * Return the value associated with the column: isread
	 */
	public Boolean isIsread () {
		return isread;
	}

	/**
	 * Set the value related to the column: isread
	 * @param isread the isread value
	 */
	public void setIsread (Boolean isread) {
		this.isread = isread;
	}



	/**
	 * Return the value associated with the column: postdate
	 */
	public java.util.Date getPostdate () {
		return postdate;
	}

	/**
	 * Set the value related to the column: postdate
	 * @param postdate the postdate value
	 */
	public void setPostdate (java.util.Date postdate) {
		this.postdate = postdate;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TMessage)) return false;
		else {
			org.yidu.novel.entity.TMessage tMessage = (org.yidu.novel.entity.TMessage) obj;
			return (this.getMessageno() == tMessage.getMessageno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getMessageno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_review table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_review"
 */

public abstract class BaseTReview  implements Serializable {

	public static String REF = "TReview";
	public static String PROP_USERNO = "userno";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_CHAPTERNO = "chapterno";
	public static String PROP_REVIEW = "review";
	public static String PROP_REVIEWNO = "reviewno";
	public static String PROP_TITLE = "title";
	public static String PROP_CHAPTERNAME = "chaptername";
	public static String PROP_EMAIL = "email";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_ARTICLENAME = "articlename";


	// constructors
	public BaseTReview () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTReview (int reviewno) {
		this.setReviewno(reviewno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int reviewno;

	// fields
	private Integer userno;
	private String loginid;
	private Integer articleno;
	private String articlename;
	private Integer chapterno;
	private String chaptername;
	private String title;
	private String review;
	private String email;
	private java.util.Date postdate;
	private Boolean deleteflag;
	private Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="reviewno"
     */
	public int getReviewno () {
		return reviewno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param reviewno the new ID
	 */
	public void setReviewno (int reviewno) {
		this.reviewno = reviewno;
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
	 * Return the value associated with the column: articleno
	 */
	public Integer getArticleno () {
		return articleno;
	}

	/**
	 * Set the value related to the column: articleno
	 * @param articleno the articleno value
	 */
	public void setArticleno (Integer articleno) {
		this.articleno = articleno;
	}



	/**
	 * Return the value associated with the column: articlename
	 */
	public String getArticlename () {
		return articlename;
	}

	/**
	 * Set the value related to the column: articlename
	 * @param articlename the articlename value
	 */
	public void setArticlename (String articlename) {
		this.articlename = articlename;
	}



	/**
	 * Return the value associated with the column: chapterno
	 */
	public Integer getChapterno () {
		return chapterno;
	}

	/**
	 * Set the value related to the column: chapterno
	 * @param chapterno the chapterno value
	 */
	public void setChapterno (Integer chapterno) {
		this.chapterno = chapterno;
	}



	/**
	 * Return the value associated with the column: chaptername
	 */
	public String getChaptername () {
		return chaptername;
	}

	/**
	 * Set the value related to the column: chaptername
	 * @param chaptername the chaptername value
	 */
	public void setChaptername (String chaptername) {
		this.chaptername = chaptername;
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
	 * Return the value associated with the column: review
	 */
	public String getReview () {
		return review;
	}

	/**
	 * Set the value related to the column: review
	 * @param review the review value
	 */
	public void setReview (String review) {
		this.review = review;
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
		if (!(obj instanceof org.yidu.novel.entity.TReview)) return false;
		else {
			org.yidu.novel.entity.TReview tReview = (org.yidu.novel.entity.TReview) obj;
			return (this.getReviewno() == tReview.getReviewno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getReviewno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
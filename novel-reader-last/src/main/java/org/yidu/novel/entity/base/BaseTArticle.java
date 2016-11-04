package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_article"
 */

public abstract class BaseTArticle  implements Serializable {

	public static String REF = "TArticle";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_AUTHORID = "authorid";
	public static String PROP_FULLFLAG = "fullflag";
	public static String PROP_CREATEUSERNO = "createuserno";
	public static String PROP_LISTDESCRIPTION = "listdescription";
	public static String PROP_PUBLICFLAG = "publicflag";
	public static String PROP_LASTCHAPTER = "lastchapter";
	public static String PROP_AUTHOR = "author";
	public static String PROP_SUBCATEGORY = "subcategory";
	public static String PROP_MONTHVISIT = "monthvisit";
	public static String PROP_ALLVOTE = "allvote";
	public static String PROP_DAYVOTE = "dayvote";
	public static String PROP_DAYVISIT = "dayvisit";
	public static String PROP_USECUSTOMIZEINFOTITLE = "usecustomizeinfotitle";
	public static String PROP_FIRSTFLAG = "firstflag";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_LISTTITLE = "listtitle";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_INITIAL = "initial";
	public static String PROP_KEYWORDS = "keywords";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_SIZE = "size";
	public static String PROP_LASTCHAPTERNO = "lastchapterno";
	public static String PROP_LASTUPDATE = "lastupdate";
	public static String PROP_USECUSTOMIZELISTTITLE = "usecustomizelisttitle";
	public static String PROP_PERMISSION = "permission";
	public static String PROP_AGENT = "agent";
	public static String PROP_WEEKVISIT = "weekvisit";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_WEEKVOTE = "weekvote";
	public static String PROP_LISTKEYWORDS = "listkeywords";
	public static String PROP_INFODESCRIPTION = "infodescription";
	public static String PROP_ALLVISIT = "allvisit";
	public static String PROP_IMGFLAG = "imgflag";
	public static String PROP_CATEGORY = "category";
	public static String PROP_INFOTITLE = "infotitle";
	public static String PROP_MONTHVOTE = "monthvote";
	public static String PROP_PINYINHEADCHAR = "pinyinheadchar";
	public static String PROP_INFOKEYWORDS = "infokeywords";
	public static String PROP_INTRO = "intro";
	public static String PROP_PINYIN = "pinyin";
	public static String PROP_CHAPTERS = "chapters";
	public static String PROP_CREATETIME = "createtime";
	public static String PROP_ARTICLENAME = "articlename";
	public static String PROP_AUTHORFLAG = "authorflag";


	// constructors
	public BaseTArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTArticle (int articleno) {
		this.setArticleno(articleno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int articleno;

	// fields
	private String articlename;
	private String pinyin;
	private String pinyinheadchar;
	private Character initial;
	private String keywords;
	private Integer authorid;
	private String author;
	private Integer category;
	private Integer subcategory;
	private String intro;
	private Integer lastchapterno;
	private String lastchapter;
	private Integer chapters;
	private Integer size;
	private Boolean fullflag;
	private Short imgflag;
	private String agent;
	private Boolean firstflag;
	private Integer permission;
	private Boolean authorflag;
	private java.util.Date postdate;
	private java.util.Date lastupdate;
	private Integer dayvisit;
	private Integer weekvisit;
	private Integer monthvisit;
	private Integer allvisit;
	private Integer dayvote;
	private Integer weekvote;
	private Integer monthvote;
	private Integer allvote;
	private Boolean deleteflag;
	private Integer publicflag;
	private Integer createuserno;
	private java.util.Date createtime;
	private Integer modifyuserno;
	private java.util.Date modifytime;
	private Boolean usecustomizeinfotitle;
	private String infotitle;
	private String infokeywords;
	private String infodescription;
	private Boolean usecustomizelisttitle;
	private String listtitle;
	private String listkeywords;
	private String listdescription;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="articleno"
     */
	public int getArticleno () {
		return articleno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param articleno the new ID
	 */
	public void setArticleno (int articleno) {
		this.articleno = articleno;
		this.hashCode = Integer.MIN_VALUE;
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
	 * Return the value associated with the column: pinyin
	 */
	public String getPinyin () {
		return pinyin;
	}

	/**
	 * Set the value related to the column: pinyin
	 * @param pinyin the pinyin value
	 */
	public void setPinyin (String pinyin) {
		this.pinyin = pinyin;
	}



	/**
	 * Return the value associated with the column: pinyinheadchar
	 */
	public String getPinyinheadchar () {
		return pinyinheadchar;
	}

	/**
	 * Set the value related to the column: pinyinheadchar
	 * @param pinyinheadchar the pinyinheadchar value
	 */
	public void setPinyinheadchar (String pinyinheadchar) {
		this.pinyinheadchar = pinyinheadchar;
	}



	/**
	 * Return the value associated with the column: initial
	 */
	public Character getInitial () {
		return initial;
	}

	/**
	 * Set the value related to the column: initial
	 * @param initial the initial value
	 */
	public void setInitial (Character initial) {
		this.initial = initial;
	}



	/**
	 * Return the value associated with the column: keywords
	 */
	public String getKeywords () {
		return keywords;
	}

	/**
	 * Set the value related to the column: keywords
	 * @param keywords the keywords value
	 */
	public void setKeywords (String keywords) {
		this.keywords = keywords;
	}



	/**
	 * Return the value associated with the column: authorid
	 */
	public Integer getAuthorid () {
		return authorid;
	}

	/**
	 * Set the value related to the column: authorid
	 * @param authorid the authorid value
	 */
	public void setAuthorid (Integer authorid) {
		this.authorid = authorid;
	}



	/**
	 * Return the value associated with the column: author
	 */
	public String getAuthor () {
		return author;
	}

	/**
	 * Set the value related to the column: author
	 * @param author the author value
	 */
	public void setAuthor (String author) {
		this.author = author;
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
	 * Return the value associated with the column: intro
	 */
	public String getIntro () {
		return intro;
	}

	/**
	 * Set the value related to the column: intro
	 * @param intro the intro value
	 */
	public void setIntro (String intro) {
		this.intro = intro;
	}



	/**
	 * Return the value associated with the column: lastchapterno
	 */
	public Integer getLastchapterno () {
		return lastchapterno;
	}

	/**
	 * Set the value related to the column: lastchapterno
	 * @param lastchapterno the lastchapterno value
	 */
	public void setLastchapterno (Integer lastchapterno) {
		this.lastchapterno = lastchapterno;
	}



	/**
	 * Return the value associated with the column: lastchapter
	 */
	public String getLastchapter () {
		return lastchapter;
	}

	/**
	 * Set the value related to the column: lastchapter
	 * @param lastchapter the lastchapter value
	 */
	public void setLastchapter (String lastchapter) {
		this.lastchapter = lastchapter;
	}



	/**
	 * Return the value associated with the column: chapters
	 */
	public Integer getChapters () {
		return chapters;
	}

	/**
	 * Set the value related to the column: chapters
	 * @param chapters the chapters value
	 */
	public void setChapters (Integer chapters) {
		this.chapters = chapters;
	}



	/**
	 * Return the value associated with the column: size
	 */
	public Integer getSize () {
		return size;
	}

	/**
	 * Set the value related to the column: size
	 * @param size the size value
	 */
	public void setSize (Integer size) {
		this.size = size;
	}



	/**
	 * Return the value associated with the column: fullflag
	 */
	public Boolean isFullflag () {
		return fullflag;
	}

	/**
	 * Set the value related to the column: fullflag
	 * @param fullflag the fullflag value
	 */
	public void setFullflag (Boolean fullflag) {
		this.fullflag = fullflag;
	}



	/**
	 * Return the value associated with the column: imgflag
	 */
	public Short getImgflag () {
		return imgflag;
	}

	/**
	 * Set the value related to the column: imgflag
	 * @param imgflag the imgflag value
	 */
	public void setImgflag (Short imgflag) {
		this.imgflag = imgflag;
	}



	/**
	 * Return the value associated with the column: agent
	 */
	public String getAgent () {
		return agent;
	}

	/**
	 * Set the value related to the column: agent
	 * @param agent the agent value
	 */
	public void setAgent (String agent) {
		this.agent = agent;
	}



	/**
	 * Return the value associated with the column: firstflag
	 */
	public Boolean isFirstflag () {
		return firstflag;
	}

	/**
	 * Set the value related to the column: firstflag
	 * @param firstflag the firstflag value
	 */
	public void setFirstflag (Boolean firstflag) {
		this.firstflag = firstflag;
	}



	/**
	 * Return the value associated with the column: permission
	 */
	public Integer getPermission () {
		return permission;
	}

	/**
	 * Set the value related to the column: permission
	 * @param permission the permission value
	 */
	public void setPermission (Integer permission) {
		this.permission = permission;
	}



	/**
	 * Return the value associated with the column: authorflag
	 */
	public Boolean isAuthorflag () {
		return authorflag;
	}

	/**
	 * Set the value related to the column: authorflag
	 * @param authorflag the authorflag value
	 */
	public void setAuthorflag (Boolean authorflag) {
		this.authorflag = authorflag;
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
	 * Return the value associated with the column: lastupdate
	 */
	public java.util.Date getLastupdate () {
		return lastupdate;
	}

	/**
	 * Set the value related to the column: lastupdate
	 * @param lastupdate the lastupdate value
	 */
	public void setLastupdate (java.util.Date lastupdate) {
		this.lastupdate = lastupdate;
	}



	/**
	 * Return the value associated with the column: dayvisit
	 */
	public Integer getDayvisit () {
		return dayvisit;
	}

	/**
	 * Set the value related to the column: dayvisit
	 * @param dayvisit the dayvisit value
	 */
	public void setDayvisit (Integer dayvisit) {
		this.dayvisit = dayvisit;
	}



	/**
	 * Return the value associated with the column: weekvisit
	 */
	public Integer getWeekvisit () {
		return weekvisit;
	}

	/**
	 * Set the value related to the column: weekvisit
	 * @param weekvisit the weekvisit value
	 */
	public void setWeekvisit (Integer weekvisit) {
		this.weekvisit = weekvisit;
	}



	/**
	 * Return the value associated with the column: monthvisit
	 */
	public Integer getMonthvisit () {
		return monthvisit;
	}

	/**
	 * Set the value related to the column: monthvisit
	 * @param monthvisit the monthvisit value
	 */
	public void setMonthvisit (Integer monthvisit) {
		this.monthvisit = monthvisit;
	}



	/**
	 * Return the value associated with the column: allvisit
	 */
	public Integer getAllvisit () {
		return allvisit;
	}

	/**
	 * Set the value related to the column: allvisit
	 * @param allvisit the allvisit value
	 */
	public void setAllvisit (Integer allvisit) {
		this.allvisit = allvisit;
	}



	/**
	 * Return the value associated with the column: dayvote
	 */
	public Integer getDayvote () {
		return dayvote;
	}

	/**
	 * Set the value related to the column: dayvote
	 * @param dayvote the dayvote value
	 */
	public void setDayvote (Integer dayvote) {
		this.dayvote = dayvote;
	}



	/**
	 * Return the value associated with the column: weekvote
	 */
	public Integer getWeekvote () {
		return weekvote;
	}

	/**
	 * Set the value related to the column: weekvote
	 * @param weekvote the weekvote value
	 */
	public void setWeekvote (Integer weekvote) {
		this.weekvote = weekvote;
	}



	/**
	 * Return the value associated with the column: monthvote
	 */
	public Integer getMonthvote () {
		return monthvote;
	}

	/**
	 * Set the value related to the column: monthvote
	 * @param monthvote the monthvote value
	 */
	public void setMonthvote (Integer monthvote) {
		this.monthvote = monthvote;
	}



	/**
	 * Return the value associated with the column: allvote
	 */
	public Integer getAllvote () {
		return allvote;
	}

	/**
	 * Set the value related to the column: allvote
	 * @param allvote the allvote value
	 */
	public void setAllvote (Integer allvote) {
		this.allvote = allvote;
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
	 * Return the value associated with the column: publicflag
	 */
	public Integer getPublicflag () {
		return publicflag;
	}

	/**
	 * Set the value related to the column: publicflag
	 * @param publicflag the publicflag value
	 */
	public void setPublicflag (Integer publicflag) {
		this.publicflag = publicflag;
	}



	/**
	 * Return the value associated with the column: createuserno
	 */
	public Integer getCreateuserno () {
		return createuserno;
	}

	/**
	 * Set the value related to the column: createuserno
	 * @param createuserno the createuserno value
	 */
	public void setCreateuserno (Integer createuserno) {
		this.createuserno = createuserno;
	}



	/**
	 * Return the value associated with the column: createtime
	 */
	public java.util.Date getCreatetime () {
		return createtime;
	}

	/**
	 * Set the value related to the column: createtime
	 * @param createtime the createtime value
	 */
	public void setCreatetime (java.util.Date createtime) {
		this.createtime = createtime;
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
	 * Return the value associated with the column: usecustomizeinfotitle
	 */
	public Boolean isUsecustomizeinfotitle () {
		return usecustomizeinfotitle;
	}

	/**
	 * Set the value related to the column: usecustomizeinfotitle
	 * @param usecustomizeinfotitle the usecustomizeinfotitle value
	 */
	public void setUsecustomizeinfotitle (Boolean usecustomizeinfotitle) {
		this.usecustomizeinfotitle = usecustomizeinfotitle;
	}



	/**
	 * Return the value associated with the column: infotitle
	 */
	public String getInfotitle () {
		return infotitle;
	}

	/**
	 * Set the value related to the column: infotitle
	 * @param infotitle the infotitle value
	 */
	public void setInfotitle (String infotitle) {
		this.infotitle = infotitle;
	}



	/**
	 * Return the value associated with the column: infokeywords
	 */
	public String getInfokeywords () {
		return infokeywords;
	}

	/**
	 * Set the value related to the column: infokeywords
	 * @param infokeywords the infokeywords value
	 */
	public void setInfokeywords (String infokeywords) {
		this.infokeywords = infokeywords;
	}



	/**
	 * Return the value associated with the column: infodescription
	 */
	public String getInfodescription () {
		return infodescription;
	}

	/**
	 * Set the value related to the column: infodescription
	 * @param infodescription the infodescription value
	 */
	public void setInfodescription (String infodescription) {
		this.infodescription = infodescription;
	}



	/**
	 * Return the value associated with the column: usecustomizelisttitle
	 */
	public Boolean isUsecustomizelisttitle () {
		return usecustomizelisttitle;
	}

	/**
	 * Set the value related to the column: usecustomizelisttitle
	 * @param usecustomizelisttitle the usecustomizelisttitle value
	 */
	public void setUsecustomizelisttitle (Boolean usecustomizelisttitle) {
		this.usecustomizelisttitle = usecustomizelisttitle;
	}



	/**
	 * Return the value associated with the column: listtitle
	 */
	public String getListtitle () {
		return listtitle;
	}

	/**
	 * Set the value related to the column: listtitle
	 * @param listtitle the listtitle value
	 */
	public void setListtitle (String listtitle) {
		this.listtitle = listtitle;
	}



	/**
	 * Return the value associated with the column: listkeywords
	 */
	public String getListkeywords () {
		return listkeywords;
	}

	/**
	 * Set the value related to the column: listkeywords
	 * @param listkeywords the listkeywords value
	 */
	public void setListkeywords (String listkeywords) {
		this.listkeywords = listkeywords;
	}



	/**
	 * Return the value associated with the column: listdescription
	 */
	public String getListdescription () {
		return listdescription;
	}

	/**
	 * Set the value related to the column: listdescription
	 * @param listdescription the listdescription value
	 */
	public void setListdescription (String listdescription) {
		this.listdescription = listdescription;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TArticle)) return false;
		else {
			org.yidu.novel.entity.TArticle tArticle = (org.yidu.novel.entity.TArticle) obj;
			return (this.getArticleno() == tArticle.getArticleno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getArticleno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
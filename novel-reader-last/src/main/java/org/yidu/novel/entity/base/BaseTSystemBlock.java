package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_system_block table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_system_block"
 */

public abstract class BaseTSystemBlock  implements Serializable {

	public static String REF = "TSystemBlock";
	public static String PROP_LIMITNUM = "limitnum";
	public static String PROP_CATEGORY = "category";
	public static String PROP_BLOCKNAME = "blockname";
	public static String PROP_BLOCKID = "blockid";
	public static String PROP_TARGET = "target";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_SORTCOL = "sortcol";
	public static String PROP_TYPE = "type";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_CONTENT = "content";
	public static String PROP_ISASC = "isasc";
	public static String PROP_ISFINISH = "isfinish";
	public static String PROP_BLOCKNO = "blockno";


	// constructors
	public BaseTSystemBlock () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTSystemBlock (int blockno) {
		this.setBlockno(blockno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int blockno;

	// fields
	private String blockid;
	private String blockname;
	private Short type;
	private Integer category;
	private String sortcol;
	private Boolean isasc;
	private Boolean isfinish;
	private Integer limitnum;
	private String content;
	private Short target;
	private Boolean deleteflag;
	private Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="blockno"
     */
	public int getBlockno () {
		return blockno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param blockno the new ID
	 */
	public void setBlockno (int blockno) {
		this.blockno = blockno;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: blockid
	 */
	public String getBlockid () {
		return blockid;
	}

	/**
	 * Set the value related to the column: blockid
	 * @param blockid the blockid value
	 */
	public void setBlockid (String blockid) {
		this.blockid = blockid;
	}



	/**
	 * Return the value associated with the column: blockname
	 */
	public String getBlockname () {
		return blockname;
	}

	/**
	 * Set the value related to the column: blockname
	 * @param blockname the blockname value
	 */
	public void setBlockname (String blockname) {
		this.blockname = blockname;
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
	 * Return the value associated with the column: sortcol
	 */
	public String getSortcol () {
		return sortcol;
	}

	/**
	 * Set the value related to the column: sortcol
	 * @param sortcol the sortcol value
	 */
	public void setSortcol (String sortcol) {
		this.sortcol = sortcol;
	}



	/**
	 * Return the value associated with the column: isasc
	 */
	public Boolean isIsasc () {
		return isasc;
	}

	/**
	 * Set the value related to the column: isasc
	 * @param isasc the isasc value
	 */
	public void setIsasc (Boolean isasc) {
		this.isasc = isasc;
	}

	/**
     * Return the value associated with the column: isfinish
     */
    public Boolean isIsfinish () {
        return isfinish;
    }

    /**
     * Set the value related to the column: isfinish
     * @param isfinish the isfinish value
     */
    public void setIsfinish (Boolean isfinish) {
        this.isfinish = isfinish;
    }

	/**
	 * Return the value associated with the column: limitnum
	 */
	public Integer getLimitnum () {
		return limitnum;
	}

	/**
	 * Set the value related to the column: limitnum
	 * @param limitnum the limitnum value
	 */
	public void setLimitnum (Integer limitnum) {
		this.limitnum = limitnum;
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
	 * Return the value associated with the column: target
	 */
	public Short getTarget () {
		return target;
	}

	/**
	 * Set the value related to the column: target
	 * @param target the target value
	 */
	public void setTarget (Short target) {
		this.target = target;
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
		if (!(obj instanceof org.yidu.novel.entity.TSystemBlock)) return false;
		else {
			org.yidu.novel.entity.TSystemBlock tSystemBlock = (org.yidu.novel.entity.TSystemBlock) obj;
			return (this.getBlockno() == tSystemBlock.getBlockno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getBlockno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}
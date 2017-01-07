package com.stt.jongo.demo.po;

import org.jongo.marshall.jackson.oid.ObjectId;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class PersonInfo /*implements Serializable*/{

//    private static final long serialVersionUID = -7125642695178165650L;

//    @ObjectId
//    private Long _id;
    private int id;
    private String name;
    private String sex;
    private String relationship;

    public PersonInfo() {
    }

    public PersonInfo(int id, String name, String sex, String relationship) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.relationship = relationship;
    }

    /*public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}

package com.stt.java.base.java8.lambdas.book.examples.chapter1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 创作音乐的个人或团队
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public final class Artist {

    //艺术家的名字（例如“甲壳虫乐队”）
    private String name;

    //乐队成员（例如“约翰· 列侬”），该字段可为空。
    private List<Artist> members;

    //书中字段名：origin：乐队来自哪里（例如“利物浦”）。
    private String nationality;

    public Artist(String name,String nationality){
        this(name, Collections.emptyList(),nationality);
    }

    public Artist(String name, List<Artist> members,String nationality){
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        Objects.requireNonNull(nationality);
        this.name = name;
        this.members = new ArrayList<>(members);
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Stream<Artist> getMembers() {
        return members.stream();
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isSolo(){
        return members.isEmpty();
    }

    public boolean isForm(String nationality){
        return this.nationality.equals(nationality);
    }

    @Override
    public String toString() {
        return getName();
    }

    public Artist copy(){
        List<Artist> members = getMembers().map(Artist::copy).collect(Collectors.toList());
        return new Artist(name,members,nationality);
    }
}

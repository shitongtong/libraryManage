package com.stt.java.base.java8.lambdas.book.examples.chapter1;

/**
 * 专辑中的一支曲目。
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public final class Track {

    //曲目名称（例如《黄色潜水艇》）。
    private String name;

    //曲目的播放时长
    private final int length;

    public Track(String name,int length){
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Track copy(){
        return new Track(name,length);
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}

package com.stt.java.base.java8.lambdas.book.examples.chapter1;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 专辑，由若干曲目组成。
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public final class Album implements Performance {

    //name：专辑名（例如《左轮手枪》）。
    private String name;

    //tracks：专辑上所有曲目的列表。
    private List<Track> tracks;

    //musicians：参与创作本专辑的艺术家列表。
    private List<Artist> musicians;

    public Album(String name,List<Track> tracks,List<Artist> musicians){
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Stream<Artist> getMusicians() {
        return this.musicians.stream();
    }

    public List<Artist> getMusicianList(){
        return Collections.unmodifiableList(musicians);
    }

    public Stream<Track> getTracks() {
        return this.tracks.stream();
    }

    public List<Track> getTrackList(){
        return Collections.unmodifiableList(tracks);
    }

    public Artist getMainMusician(){
        return this.musicians.get(0);
    }

    public Album copy(){
        List<Track> tracks = getTracks().map(Track::copy).collect(Collectors.toList());
        List<Artist> musicians = getMusicians().map(Artist::copy).collect(Collectors.toList());
        return new Album(name,tracks,musicians);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                ", musicians=" + musicians +
                '}';
    }
}

package com.stt.java.base.java8.lambdas.book.examples.chapter4;

import com.stt.java.base.java8.lambdas.book.examples.chapter1.Artist;

import java.util.List;
import java.util.Optional;

/**
 * 重构Artists的getArtist的方法
 *
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class ArtistsRefactoring {

    private List<Artist> artists;

    public ArtistsRefactoring(List<Artist> artists){
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index) {
            Optional<Artist> optional = getArtist(index);
            if (optional.isPresent()){
                return optional.get().getName();
            }else{
                return "unknown";
            }
    }

}

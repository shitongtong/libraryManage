//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.model;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<String> words;

    public Category() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWords() {
        return this.words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String toString() {
        return this.id + "," + this.name + "," + this.words.toString();
    }
}

package cn.stt.sorl;

import java.util.List;

/**
 * Created by Administrator on 2016-11-07.
 */
public class Item {

    private String id;

    private String[] categories;

    private List<String> features;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}

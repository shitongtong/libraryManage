package cn.stt.po;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by stt on 2016/9/3.
 */
@Data
public class Book implements Serializable {

    private String name;
    private Integer stockCount;
    private Integer borrowCount;
    private Integer totalCount;
}

package com.tt.springboot.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2016-12-19.
 */
public class PersonForm {
    //名字不能为空，而且长度必须在2和30之间
    @NotNull(message = "名称不能为空")
    @Size(min=2, max=30, message = "名字长度必须在2和30之间")
    private String name;

    //年龄不能为空，而且必须大于等于18岁
    @NotNull
    @Min(18)
    private Integer age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}

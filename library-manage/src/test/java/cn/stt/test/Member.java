package cn.stt.test;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试用实体类
 * Created by stt on 2016/9/3.
 */
@Data
public class Member implements Comparable<Member>{

    // 格式化日期用
    private static final SimpleDateFormat MY_SDF = new SimpleDateFormat(
            "yyyy-MM-dd");

    // 几个属性
    private int id;
    private String username;
    private int level;
    private Date birthday;

    public Member(){}

    // 构造函数
    public Member(int id, String username, int level, String birthday) throws Exception {
        this.id = id;
        this.username = username;
        this.level = level;
        this.birthday = new Date(MY_SDF.parse(birthday).getTime());
    }

    // 注意：如果使用MySortList类，则此方法不再需要。
    // 因为此方法是提供给Collections.sort方法使用的。
    public int compareTo(Member o) {
        // 只能对一个字段做比较，如果做整个对象的比较就实现不了按指定字段排序了。
        return this.getUsername().compareTo(o.getUsername());
    }
}

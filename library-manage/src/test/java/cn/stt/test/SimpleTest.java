package cn.stt.test;

import cn.stt.po.Book;
import cn.stt.util.ListSortUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by stt on 2016/9/3.
 */
public class SimpleTest {

    @Test
    public void testMember() throws Exception {

        // 生成自定义对象，然后对它按照指定字段排序
        List<Member> listMember = new ArrayList<Member>();
        listMember.add(new Member(1, "wm123", 3, "1992-12-01"));
        listMember.add(new Member(2, "a234", 8, "1995-12-01"));
        listMember.add(new Member(3, "m456", 12, "1990-12-01"));
        System.out.println("Member当前顺序...");
        System.out.println(listMember);

        // 方式一排序输出
        System.out.println("Member默认排序（用自带的compareTo方法）后...");
        Collections.sort(listMember);
        System.out.println(listMember);
        System.out.println("Member倒序（用自带的compareTo方法）后...");
        Collections.sort(listMember, Collections.reverseOrder());
        System.out.println(listMember);

        // 方式二排序输出
        ListSortUtil<Member> msList = new ListSortUtil<Member>();
        msList.sortByMethod(listMember, "getUsername", false);
        System.out.println("Member按字段用户名排序后...");
        System.out.println(listMember);

        msList.sortByMethod(listMember, "getLevel", false);
        System.out.println("Member按字段级别排序后...");
        System.out.println(listMember);

        msList.sortByMethod(listMember, "getBirthday", true);
        System.out.println("Member按字段出生日期倒序后...");
        System.out.println(listMember);

        System.out.println("----------------------------------");
        ListSortUtil<Member> msList1 = new ListSortUtil<Member>();
        msList1.sortByField(listMember, "birthday", true);
        System.out.println(listMember);

    }
}

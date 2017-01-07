package com.stt.java.base.annotation.demo3;

import com.stt.java.base.annotation.demo2.FruitColor;
import com.stt.java.base.annotation.demo2.FruitProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 运行时获取注解的基类
 *
 * Created by Administrator on 2017-01-06.
 *
 * @author shitongtong
 */
public class Parent<T> {

    private Class<T> entity;

    public Parent() {
        init();
    }

    @SuppressWarnings("unchecked")
    public List<SortableField> init(){
        List<SortableField> list = new ArrayList<>();
        /*
         * getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）
         * 的直接超类的 Type(Class<T>泛型中的类型)，然后将其转换ParameterizedType。。
         *  getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
         *  [0]就是这个数组中第一个了。。
         *  简而言之就是获得超类的泛型参数的实际类型。。
         */
        /*Type type = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        entity = (Class<T>) actualTypeArguments[0];*/

        entity = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        if (this.entity != null) {
            /*
             * 返回类中所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段
             * entity.getFields();只返回对象所表示的类或接口的所有可访问公共字段
             * 在class中getDeclared**()方法返回的都是所有访问权限的字段、方法等；
             * 可看API
             */
            Field[] fields = entity.getDeclaredFields();

            for (Field field : fields){
                //获取字段中包含fieldMeta的注解
                FieldMeta fieldMeta = field.getAnnotation(FieldMeta.class);
                if (fieldMeta != null) {
                    SortableField sortableField = new SortableField(fieldMeta, field);
                    list.add(sortableField);
                }
            }

            //返回对象所表示的类或接口的所有可访问公共方法
            Method[] methods = entity.getMethods();
            for (Method method : methods){
                FieldMeta fieldMeta = method.getAnnotation(FieldMeta.class);
                if (fieldMeta != null) {
                    SortableField sortableField = new SortableField(fieldMeta, method.getName(), method.getReturnType());
                    list.add(sortableField);
                }
            }

            //这种方法是新建FieldSortCom类实现Comparator接口，来重写compare方法实现排序
            Collections.sort(list, new Comparator<SortableField>() {
                @Override
                public int compare(SortableField o1, SortableField o2) {
                    return o1.getFieldMeta().order() - o2.getFieldMeta().order();
//                    return o1.getName().compareTo(o2.getName());//也可以用compare来比较
                }
            });
        }

        return list;
    }

    /*
        测试先判断注解是否存在再获取其值，与先获取再判断，哪个执行时间更短
        结果：先获取再判断执行时间更短几乎为0
     */
    public static void main(String[] args) {
        Field[] fields = Anno.class.getDeclaredFields();
        Field field = fields[0];
        long currentTimeMillis = System.currentTimeMillis();
        if (field.isAnnotationPresent(FieldMeta.class)){
            FieldMeta fieldMeta = field.getAnnotation(FieldMeta.class);
            System.out.println(fieldMeta.name());
        }
        System.out.println(-currentTimeMillis + System.currentTimeMillis());//11
        System.out.println("=========================================");
        long currentTimeMillis2 = System.currentTimeMillis();
        FieldMeta fieldMeta = field.getAnnotation(FieldMeta.class);
        if (fieldMeta != null) {
            System.out.println(fieldMeta.name());
        }
        System.out.println(-currentTimeMillis2 + System.currentTimeMillis());//0
    }

}

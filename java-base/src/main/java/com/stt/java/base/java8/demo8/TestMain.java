package com.stt.java.base.java8.demo8;

import com.stt.java.base.java8.demo4.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {
        //Lambda表达式中是无法访问到默认方法的，以下代码将无法编译：
//        Formula formula = (a) -> sqrt( a * 100);

    }

    /*
        Predicate接口
        Predicate 接口只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate
        组合成其他复杂的逻辑（比如：与，或，非）：
     */
    @Test
    public void testPredicate() {

        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("dd"));//true
        System.out.println(predicate.negate().test("dd"));//false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /*
        Function 接口
        Function 接口有一个参数并且返回一个结果，
        并附带了一些可以和其他函数组合的默认方法（compose, andThen）：
     */
    @Test
    public void testFunction() {

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        Integer apply1 = toInteger.apply("123");
        String apply = backToString.apply("123");
        System.out.println(apply1);//123
        System.out.println(apply);//"123"
    }

    /*
    Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
     */
    @Test
    public void testSupplier() {

        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();//==new Person();
    }

    /*
    Consumer 接口表示执行在单个参数上的操作。
     */
    @Test
    public void testConsumer() {
        Consumer<Person> personConsumer = (p) -> System.out.println("Hello," + p.getFirstName());
        personConsumer.accept(new Person("jkdsadasjk", "kldsakljkld"));
    }

    /*
    Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
     */
    @Test
    public void testComparator() {

        Comparator<Person> personComparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Person p1 = new Person("dsdsdsd", "rert");
        Person p2 = new Person("dsadds", "qwer");
        int compare = personComparator.compare(p1, p2);
        int compare1 = personComparator.reversed().compare(p1, p2);
        System.out.println(compare);//3
        System.out.println(compare1);//-3
    }

    /*
     Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，
     这是下一届中将要用到的重要概念，现在先简单的看看这个接口能干什么：
    Optional 被定义为一个简单的容器，其值可能是null或者不是null。
    在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional。
     */
    @Test
    public void testOptional() {
        String s1 = null;
        Optional<String> optional = Optional.of(s1);//bam    ""   null(异常)
        boolean present = optional.isPresent();
        System.out.println(present);//true  ture
        String str = optional.get();
        System.out.println(str);//bam   ""
        String fallback = optional.orElse("fallback");
        System.out.println(fallback);//bam  ""
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));//b   异常：角标越界
    }

    /*
    Stream 接口
    java.util.Stream 表示能应用在一组元素上一次执行的操作序列。Stream 操作分为中间操作或者最终操作两种，
    最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。
    Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。Stream的操作可以串行执行或者并行执行。
     */
    private List<String> getList() {
        //创建数据源--list
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        return stringCollection;
    }

    /*
     Filter 过滤
     过滤通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作，
     所以我们可以在过滤后的结果来应用其他Stream操作（比如forEach）。forEach需要一个函数来对过滤后的元素依次执行。
     forEach是一个最终操作，所以我们不能在forEach之后来执行其他Stream操作。
     */
    @Test
    public void testFilter() {
        List<String> list = getList();
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);//aaa2,aaa1
    }

    /*
    Sorted 排序
    排序是一个中间操作，返回的是排序好后的Stream。如果你不指定一个自定义的Comparator则会使用默认排序。
     */
    @Test
    public void testSorted() {
        List<String> list = getList();
        //排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的
        list.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);//aaa1,aaa2
        list.stream().filter(s -> s.startsWith("a")).sorted().forEach(System.out::println);//aaa1,aaa2
    }

    /*
    Map 映射
    中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象，下面的示例展示了将字符串转换为大写字符串。
    你也可以通过map来讲对象转换成其他类型，map返回的Stream类型是根据你map传递进去的函数的返回值决定的。
     */
    @Test
    public void testStreamMap() {
        List<String> list = getList();
        list.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
    }

    /*
    Match 匹配
    Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是最终操作，并返回一个boolean类型的值。
     */
    @Test
    public void testMatch() {
        List<String> list = getList();
        System.out.println(list.stream().anyMatch(s -> s.startsWith("a")));//true
        System.out.println(list.stream().allMatch(s -> s.startsWith("a")));//false
        System.out.println(list.stream().noneMatch(s -> s.startsWith("z")));//true
    }

    /*
    Count 计数
    计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
     */
    @Test
    public void testCount() {
        List<String> list = getList();
        System.out.println(list.stream().count());//8
        System.out.println(list.stream().filter(s -> s.startsWith("b")).count());//3
    }

    /*
    Reduce 规约
    这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规约后的结果是通过Optional接口表示的：
     */
    @Test
    public void testReduce() {
        List<String> list = getList();
        Optional<String> reduce = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);//aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
        System.out.println(reduce.toString());//Optional[aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2]
        System.out.println(reduce.get());//aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
    }

    /*
    并行Streams
    前面提到过Stream有串行和并行两种，串行Stream上的操作是在一个线程中依次完成，而并行Stream则是在多个线程上同时执行。
    下面的例子展示了是如何通过并行Stream来提升性能：
     */
    @Test
    public void testStream() {
        //首先我们创建一个没有重复元素的大表：
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        //然后我们计算一下排序这个Stream要耗时多久，
        //串行排序：
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took:%d ms", millis));
        //1,    sequential sort took:1024 ms
        //2,    sequential sort took:972 ms
        //3,    sequential sort took:1056 ms
        //4,    sequential sort took:1008 ms
        System.out.println(count);//1000000

        //并行排序：
        long t2 = System.nanoTime();
        long count2 = values.parallelStream().sorted().count();
        long t3 = System.nanoTime();
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took:%d ms", millis2));
        //1,    parallel sort took:4157 ms
        //2,    parallel sort took:557 ms
        //3,    parallel sort took:559 ms
        //4,    parallel sort took:486 ms
        System.out.println(count2);//1000000
    }

    /*
     Map
     前面提到过，Map类型不支持stream，不过Map提供了一些新的有用的方法来处理一些日常任务。
     */
    @Test
    public void testMap() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
//        map.forEach((id,value)-> System.out.println(value));
//
//        Map<Integer, Person> personMap = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            Person person = new Person("11", "11");
//            personMap.putIfAbsent(i,person);
//        }
//        personMap.forEach((id,value)-> System.out.println(value));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));//val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false
        System.out.println(map.get(9));

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true
        System.out.println(map.get(23));

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

//        map.computeIfPresent(3,(num, val) -> "bam");
//        map.get(3);             // bam

        //在Map里删除一个键值全都匹配的项：
        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        map.getOrDefault(42, "not found");  // not found

        //Map的元素做合并
        //如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));              // val9concat
    }

}

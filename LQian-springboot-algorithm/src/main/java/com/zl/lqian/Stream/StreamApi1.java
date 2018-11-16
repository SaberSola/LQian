package com.zl.lqian.Stream;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApi1 {

    /**
     *
     *  Stream 和 collection 有以下不同
     *
     *  无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
     *
     *  为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
     *
     *  惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
     *
     *  可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
     *
     *  Stream 主要分为俩部分
     *
     *  1：中间操作总是会惰式执行，调用中间操作只会生成一个标记了该操作的新stream，仅此而已。
     *
     *  2：结束操作会触发实际计算，计算发生时会把所有中间操作积攒的操作以pipeline的方式执行，这样可以减少迭代次数。计算完成之后stream就会失效。
     *
     *  中间操作Api:
     *
     *  concat() distinct() filter() flatMap() limit() map() peek()
     *  skip() sorted() parallel() sequential() unordered()
     *
     *  结束操作Api：
     *
     *  allMatch() anyMatch() collect() count() findAny() findFirst()
     *  forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
     *
     *  @param args
     */

    public static void main(String[] args){





    }

    @Test
    public void concat(){

        /**
         *
         * concat 是将俩个Stream合并成一个Stream 保持原来顺序
         */

        Collection<String> abc = Arrays.asList("a", "b", "c");
        Collection<String> digits = Arrays.asList("1", "3", "2");
        Collection<String> greekAbc = Arrays.asList("alpha", "beta", "gamma");

       final Stream<String> concat = Stream.concat(abc.stream(),digits.stream());

       concat.forEach((string)->{
           System.out.println(string);
       });

    }

    /**
     * s是清楚相同的元素
     */
    @Test
    public void distinct(){


        Collection<String> list = Arrays.asList("A", "B", "C", "D", "A", "B", "C");

        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());

        System.out.println(distinctElements);


        //但是distinct 不能对对象属性进行去重需要加入方法

        User user = new User(1,"zl");

        User user1 = new User(2,"zwq");

        User user2 = new User(3,"zlsdf");

        User user3 = new User(1,"zlsf");


    }

    /**
     *
     * filter stream 进行过滤
     */

    @Test
    public void filter(){

        User user = new User(1,"zl");

        User user1 = new User(2,"zwq");

        User user2 = new User(3,"zlsdf");

        User user3 = new User(1,"zlsf");

        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);


        List<User> userList = users.stream().filter((user4)->{return user4.getAge() == 1;}).collect(Collectors.toList());

        userList.stream().forEach(System.out::println);

        //使用Filter 取出某种重复

        userList = users.stream().filter(distinctByKey((User::getAge))).collect(Collectors.toList());
        userList.stream().forEach(System.out::println);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

    /**
     *
     *  flatMap是将一个Stream中的每个元素转换为另一个Stream中的另一种元素对象
     *
     *
     *
     */
    @Test
    public void  flatMap(){


        List<Foo> foos = new ArrayList<>();

        // create foos
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

        // create bars
        foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));  //获取里边某个属性的

        /**
         * 还可以转为
         */
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)        // 上一步产生的Foo对象
                        .mapToObj(i -> new Bar("Bar" + i + " <- "+  f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        /**
         * FlatMap也支持JAVA8中新引入的Optional类，
         * Optionals flatMap能返回一个另外的类的optional包装类，可以用来减少对null的检查。
         */
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }

        //可以转换为下边
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }

    @Test
    public void limit(){

        /**
         * limit方法指定数量的元素的流。对于串行流，这个方法是有效的，这是因为它只需返回前n个元素即可，
         * 但是对于有序的并行流，它可能花费相对较长的时间，如果你不在意有序，可以将有序并行流转换为无序的，可以提高性能。
         */

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> l = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(l);//[1, 2, 3, 4, 5]

    }

    @Test
    public void skip(){
        /**
         *
         * 丢弃前边n位元素
         *
         */

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> l = list.stream().limit(3).skip(1).collect(Collectors.toList());
        System.out.println(l);//[1, 2, 3, 4, 5]

    }

    @Test
    public void sorted(){

        /**
         * sorted()将流中的元素按照自然排序方式进行排序，如果元素没有实现Comparable，
         * 则终点操作执行时会抛出java.lang.ClassCastException异常。
         */

        String[] arr = new String[]{"b_123","c+342","b#632","d_123"};

        List<String> l  = Arrays.stream(arr)
                .sorted((s1,s2) -> {                   //这里实现CompareTo方法
                    if (s1.charAt(0) == s2.charAt(0))
                        return s1.substring(2).compareTo(s2.substring(2));
                    else
                        return s1.charAt(0) - s2.charAt(0);
                })
                .collect(Collectors.toList());
        System.out.println(l);

    }


    /**
     *
     * 并行流
     * 为了提高大量输入时的执行效率，stream可以采用并行的放行执行。
     * 并行流（Parallel Streams）通过ForkJoinPool.commonPool() 方法获取一个可用的ForkJoinPool
     *
     */
    @Test
    public void parallel(){

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }

    /**
     *
     *   allMatch() anyMatch() collect() count() findAny() findFirst()
     *  forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
     *
     */

    @Test
    public void match(){

        /**
         * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
         *
         * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
         *
         * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
         *
         */

        User user = new User(10,"zl");

        User user1 = new User(21,"zwq");

        User user2 = new User(34,"zlsdf");

        User user3 = new User(16,"zlsf");

        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        boolean isAllAdult = users.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = users.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);


    }

    /**
     * 返回元素个数
     */
    @Test
    public void count(){

        String[] arr = new String[]{"b_123","c+342","b#632","d_123"};

        System.out.println(Arrays.stream(arr).count());

   }

    /**
     * find 操作
     * 实际上测试结果发现，findFirst()和findAny()返回的都是第一个元素，那么两者之间到底有什么区别？
     * 通过查看javadoc描述，大致意思是findAny()是为了提高并行操作时的性能，如果没有特别需要，还是建议使用findAny()方法。
     */

    @Test
    public void find(){

         String element = Stream.of("Java", "C#", "PHP", "C++", "Python")

                // .findFirst()     // 查找第一个元素
                .findAny().get();

         System.out.println(element);
    }


    @Test
    public void maxMin(){

        List<Book> books = Arrays.asList(
                new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
                new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
                new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D)
        );

        // 计算所有图书的总价
        Optional<Double> totalPrice = books.stream()
                .map(Book::getPrice)
                .reduce((n, m) -> n + m);

        // 价格最高的图书
        Optional<Book> expensive = books.stream().max(Comparator.comparing(Book::getPrice));
        // 价格最低的图书
        Optional<Book> cheapest = books.stream().min(Comparator.comparing(Book::getPrice));
         // 计算总数
        long count = books.stream().count();
    }


    /**
     * 将stream 转为数组
     */
    @Test
    public void toarray(){
        List<Book> books = Arrays.asList(
                new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
                new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
                new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D)
        );

        System.out.println(books.stream().toArray());

    }



    class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }
    class Outer {
        Nested nested;
    }

    class Nested {
        Inner inner;
    }

    class Inner {
        String foo;
    }


}

package com.CompletableFutureDemo;

import com.CompletableFutureDemo.domain.Persion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JDKStreamLearn {

    @Test
    public void  initialization(){
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        IntStream.of(new int[]{1,2,3}).forEach(System.out::println);// 1,2,3
        System.out.println("/*************************************************************/");
        IntStream.range(1,3).forEach(System.out::println);
        System.out.println("/*************************************************************/");
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        System.out.println("/*************************************************************/");
    }

    @Test
    public void streamChange(){
        String [] strArray = new String[] {"a", "b", "c"};
        List<String> list = Arrays.asList(strArray);
        //Stream<String> stream = list.stream();
     /*   //Stream 转为数组
        String[] strArray1 = stream.toArray(String[]::new);
        System.out.println(strArray1.toString());*/

        //TODO 注意一个stream只能使用一次
      /*  List<String> list1 = stream.collect(Collectors.toList());
        System.out.println(list1.toString());*/

      /*  String string = stream.collect(Collectors.joining()).toString();
        System.out.println(string);*/
        /**
         * map()它的作用就是把 input Stream 的每一个元素，
         * 映射成 output Stream 的另外一个元素。
         * 1:先转为stream
         * 2:使用map函数转换元素
         * 3:转为新的list
         */
        List<String> output = list.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(output.toString());

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        System.out.println(squareNums.toString());

        /**
         * flatMap() 类似于合并
         */
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        System.out.println(outputStream.collect(Collectors.toList()).toString());

        /**
         * filter 过滤作用
         */
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        System.out.println(evens);
    }


    @Test
    public void testForEach(){
        Persion persion = new Persion("zl","男","16");
        Persion persion1 = new Persion("zl","男","17");
        Persion persion2 = new Persion("zl","女","168");
        Persion persion3 = new Persion("zl","女","19");
        Persion persion4 = new Persion("zl","男","20");
        List<Persion> persions = new ArrayList<>();
        persions.add(persion);
        persions.add(persion1);
        persions.add(persion2);
        persions.add(persion3);
        persions.add(persion4);

        //forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
        persions.stream().filter(p -> p.getSex().equals("女")).forEach(persion5 -> System.out.println(persion5.getSex()+persion5.getAge() + persion5.getName()) );
    }

    /**
     * 这个方法的主要作用是把 Stream 元素组合起来。
     * 它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
     * 和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说
     */
    @Test
    public void testReduce(){
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
         // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println(concat);
    }

}

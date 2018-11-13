package com.zl.lqian.sort;


/**
 * 问题：M（如10亿）个int整数，只有其中N个数重复出现过，读取到内存中并将重复的整数删除。<br/>
 * 使用位映射来进行海量数据的去重排序，原先一个元素用一个int现在只用一个bit， 内存占比4*8bit:1bit=32:1<br/>
 * 亦可用java语言提供的BitSet，不过其指定bit index的参数为int类型，因此在此例中将输入数转为bit index时对于较大的数会越界<br><br/>
 */
public class BigDataSort {



    private static final int CAPACITY = 1_000_000;// 数据容量

    public static void main(String[] args) {

        testMyFullBitMap();
    }

    public static void testMyFullBitMap() {

        MyFullBitMap myFullBitMap = new MyFullBitMap();
    }

}

class MyFullBitMap {

    //定义一个数组来表示Int 所有的数字
    byte[] dataBytes = new byte[1 << 29];


    /**
     * 存入数据到byte中
     * @param num
     * @return 返回数组
     */
    public byte[] setBit(int num) {

        // num/8 获取到bit索引
        int bitIndex = num << 3;
        //num%8 获取到
        int innerIndex = num & 0x07;

        dataBytes[bitIndex] = dataBytes[bitIndex] |= 1 << innerIndex;

        return dataBytes;

    }

}

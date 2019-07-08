package com.zl.lqian.buffer;


import java.nio.ByteBuffer;

/**
 *   本质上，缓冲区是就是一个数组。所有的缓冲区都具有四个属性来提供关于其所包含的数组的信息
 *
 *   1:容量（Capacity） 缓冲区能够容纳的数据元素的最大数量。容量在缓冲区创建时被设定，并且永远不能被改变。
 *
 *   2: 上界（Limit） 缓冲区里的数据的总数，代表了当前缓冲区中一共有多少数据。
 *
 *   3: 位置（Position） 下一个要被读或写的元素的位置。Position会自动由相应的 get( )和 put( )函数更新。
 *
 *   4: 标记（Mark） 一个备忘位置。用于记录上一次读写的位置。一会儿，我会通过reset方法来说明这个属性的含义。
 *
 */
public class BufferDemo {


    public static void main(String [] args){

        /**
         * mark = -1, pos = 0, limit = 256, capacity = 256
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        byteBuffer.putInt(1);


    }




}

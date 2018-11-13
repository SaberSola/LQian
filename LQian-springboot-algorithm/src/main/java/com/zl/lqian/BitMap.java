package com.zl.lqian;


/**
 *
 * 一个byte是占8个bit，如果每一个bit的值就是有或者没有，也就是二进制的0或者1，如果用bit的位置代表数组值有还是没有，那么0代表该数值没有出现过，
 * 1代表该数组值出现过。不也能描述数据了吗
 */
public class BitMap {

    private byte[] bits;//保存的数据

    private int capacity;//能够保存多少数据

    public BitMap(int capacity){
        this.capacity = capacity;
        //1bit能存储8个数据，那么capacity数据需要多少个bit呢，capacity/8+1,右移3位相当于除以8
        bits = new byte[(capacity >> 3) + 1];
    }
    //add 的目的 是将所在的位置变成1 其他位置不变
    public void  add(int num){
        //先获取index
        int arraryIndex = num >> 3;
        //num % 8 得到 在byte[index] 中的位置
        //余数相当于 & 反码
        int position = num & 0x07;
        //将 1 左移position 位置后 在与原数 取或 这样肯定是1
        bits[arraryIndex] |= 1 << position;
    }

    public boolean contain(int num){
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;
        //将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
        return (bits[arrayIndex] & (1 << position)) !=0;  //判断是否存在
    }

    public void clear (int num){

        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;
        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        bits[arrayIndex] &= ~(1 << position);
    }

    public static void main(String[] args){

      /*  int a = 7%8;
        int b = 7&0x07;

        System.out.println(a +" "+ b);*/

       /* BitMap bitmap = new BitMap(100);
        bitmap.add(7);
        System.out.println("插入7成功");

        boolean isexsit = bitmap.contain(7);
        System.out.println("7是否存在:"+isexsit);

        bitmap.clear(7);
        isexsit = bitmap.contain(7);
        System.out.println("7是否存在:"+isexsit);
*/

        long bitIndex = 654123211 + (1l << 31); // 获取num数据对应bit数组（虚拟）的索引
        int index = (int) (bitIndex / 8); // bit数组（虚拟）在byte数组中的索引

        int rightIndex = 654123211 >> 3;

        System.out.println(index);

        System.out.println(rightIndex);


    }
}

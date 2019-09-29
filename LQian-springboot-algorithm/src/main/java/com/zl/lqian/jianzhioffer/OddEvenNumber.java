package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29
 * @Des  输入一个数组 使奇数排在偶数的前面
 */
public class OddEvenNumber {


    /**
     * 移动偶数位置
     * @param array
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0){
            return;
        }
        for (int i = 1; i < array.length; i ++){

            int j = i - 1;
            if (array[i] % 2 != 0){//偶数
                while (j >= 0){     //j是 小于 i 的指针
                    if (array[j] % 2 != 0) {
                        break;
                    }
                    if (array[j] % 2 == 0){ //偶数
                        //
                    }

                }
            }
        }

    }

}

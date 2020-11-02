package com.zl.lqian.onehundred.sort;

public class InsertSort {

    public int[] sort(int[] sourceArray){

        for (int i=1; i < sourceArray.length; i ++){
            int insert = sourceArray[i];
            int j = i;
            while (j > 0 && insert < sourceArray[j -1]){
                sourceArray[j] = sourceArray[j-1];
                j--;
            }
            if (j!=i){
                sourceArray[j] = insert;
            }
        }
        return sourceArray;
    }
}

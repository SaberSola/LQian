package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-16
 * @Des ${todo}
 */
public class Solution48 {


    /**
     * 杨辉三角的问题
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows == 0){
            return triangle;
        }
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        for(int i = 1; i< numRows; i ++){
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(i-1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }

        return triangle;

    }
}

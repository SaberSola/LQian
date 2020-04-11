package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution49 {

    /**
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     * @param str
     * @return
     */


    public int StrToInt2(String str) {
        // \d代表[0-9] 但是要写成\\d才行。
        if(!str.matches("[+,-]?\\d+")) return 0;
        int len = str.length();
        int i = len-1;
        long res = 0;  //long类型，避免溢出。不能用int

        while(i>=0&&str.charAt(i)>='0'&&str.charAt(i)<='9'){
            res += Math.pow(10,len-1-i)*(str.charAt(i)-'0');
            i--;
        }
        res = (str.charAt(0) == '-' ? -res : res);
        //溢出就返回0，用long类型的res来比较，
        //如果定义为int res,那再比较就没有意义了，int范围为[-2147483648,2147483647]
        if(res>Integer.MAX_VALUE|| res<Integer.MIN_VALUE)return 0;
        return (int)res;
    }

    public int StrToInt(String str) {
        //最优解
        if(str == null || "".equals(str.trim()))return 0;
        str = str.trim();
        char[] arr = str.toCharArray();
        int i = 0;
        int flag = 1;
        int res = 0;
        if(arr[i] == '-'){
            flag = -1;
        }
        if( arr[i] == '+' || arr[i] == '-'){
            i++;
        }
        while(i<arr.length ){
            //是数字
            if(isNum(arr[i])){
                int cur = arr[i] - '0';
                if(flag == 1 && (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && cur >7)){
                    return 0;
                }
                if(flag == -1 && (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && cur >8)){
                    return 0;
                }
                res = res*10 +cur;
                i++;
            }else{
                //不是数字
                return 0;
            }
        }
        return res*flag;
    }
    public static boolean isNum(char c){
        return c>='0'&& c<='9';
    }
}

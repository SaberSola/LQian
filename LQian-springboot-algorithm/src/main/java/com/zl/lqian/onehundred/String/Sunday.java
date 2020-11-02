package com.zl.lqian.onehundred.String;

public class Sunday {

    /**
     * 实现 strStr() 函数。给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
     * @param origin
     * @param aim
     * @return
     */
    public static int strStr(String origin, String aim) {
        if (origin == null || aim == null){
            return 0;
        }
        if (origin.length() < aim.length()) {
            return -1;
        }
        //目标传匹配
        int originIndex = 0;
        int aimIndex = 0;
        while (aimIndex < aim.length()){
            // 针对origin匹配完，但aim未匹配完情况处理 如 mississippi sippia
            if (originIndex > origin.length() - 1) {
                return -1;
            }
            if (origin.charAt(originIndex) == aim.charAt(aimIndex)){
                originIndex++;
                aimIndex++;
            }else {
                int nextCharIndex = originIndex - aimIndex + aim.length();
                //判断下一个目标字符是否存在
                if(nextCharIndex < origin.length()){
                    // 判断目标字符在模式串中匹配到，返回最后一个匹配的index
                    int step = aim.lastIndexOf(origin.charAt(nextCharIndex));
                    if (step == -1){
                        //不存在的话,设置到目标字符的下一个元素
                        originIndex = nextCharIndex + 1;
                    }else {
                        //存在的话.移动到对应的数字
                        originIndex = nextCharIndex -step;
                    }
                    //模式穿总是从第一个匹配
                    aimIndex = 0;
                }else {
                    return -1;
                }
            }
        }
        return originIndex - aimIndex;
    }

    public static void main(String[] args) {

        System.out.println(strStr("helllowordzhanglei","zhanglei"));

    }
}

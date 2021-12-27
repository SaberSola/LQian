package com.zl.lqian.bytedance;

public class IsHappy {

    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：
     * <p>
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     *
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     *
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return 重复就说明有环, 快慢指针
     */
    public boolean isHappy(int n) {
        //快慢指针
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && fastRunner != slowRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;

    }

    public int getNext(int number) {
        int total = 0;
        while (number > 0) {
            int d = number % 10;
            number = number / 10;
            total = total + d * d;
        }
        return total;
    }
}

package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 求众数 II
 * 我们用哈希统计数组中每个元素出现的次数，设数组的长度为 nn，返回所有统计次数超过 \lfloor\frac{n}{3}\rfloor⌊
 * 3
 * n
 * ​
 * ⌋ 的元素。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/majority-element-ii/solution/qiu-zhong-shu-ii-by-leetcode-solution-y1rn/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MajorityElement {
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (cnt.containsKey(nums[i])) {
                cnt.put(nums[i], cnt.get(nums[i]) + 1);
            } else {
                cnt.put(nums[i], 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : cnt.keySet()) {
            if (cnt.get(x) > nums.length / 3) {
                ans.add(x);
            }
        }

        return ans;
    }
}

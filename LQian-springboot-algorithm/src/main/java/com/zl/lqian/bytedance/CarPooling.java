package com.zl.lqian.bytedance;

public class CarPooling {

    /**
     * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
     *
     * 这儿有一份乘客行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了第 i 组乘客的行程信息：
     *
     * 必须接送的乘客数量；
     * 乘客的上车地点；
     * 以及乘客的下车地点。
     * 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
     *
     * 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/car-pooling
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        if(trips.length == 0){
            return true;
        }
        //找出最大值（最后一个下车的地点）
        int max = 0;
        for(int i = 0; i < trips.length; i++){
            if(max < trips[i][2]){
                max = trips[i][2];
            }
        }
        int[] start = new int[max + 1];//记录哪个点有多少人上车
        int[] end = new int[max + 1];//记录哪个点有多少人下车
        for(int i = 0; i < trips.length; i++){
            //此处为累加！
            start[trips[i][1]] += trips[i][0];
            end[trips[i][2]] += trips[i][0];
        }
        //模拟上下车
        int count = 0;//车里的人数
        for(int i = 0; i < max; i++){
            count -= end[i];
            count += start[i];
            if(count > capacity){
                return false;
            }
        }
        return true;
    }
}

package com.zl.lqian.onehundred.String;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindMinHeightTrees {

    /**
     * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
     * <p>
     * 格式
     * <p>
     * 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
     * <p>
     * 你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-height-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param edges
     * @return
     */
    //构建图，循环遍历图，找出叶子节点。去除叶子节点。知道图中节点只剩下2个或1个。返回剩下的节点。
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        // 一个节点
        if (n == 1) {
            res.add(0);
            return res;
        }
        //各个节点的出度表
        int[] degree = new int[n];
        //建立图关系，在每个节点的list中存储相连节点
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            //出度
            degree[edge[1]]++;
            //添加相邻接点
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        //创建队列
        Queue<Integer> queue  = new LinkedList<>();
        for (int i=0;i <n; i++){
            //出度为i的为叶子节点
            if (degree[i] == 1){
                queue.offer(i);
            }
        }
        //开始bfs
        while (!queue.isEmpty()){
            res = new ArrayList<>();
            //这是每一层节点的数量
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        /*如果是叶子节点我们就入队*/
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }

}

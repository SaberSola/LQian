package com.lqian.design.State.VoState;

/**
 * 具体状态类正常投票
 */
public class NormalVoteState implements VoteState  {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {

        //正常投票,开始记录
        //正常投票，记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }
}

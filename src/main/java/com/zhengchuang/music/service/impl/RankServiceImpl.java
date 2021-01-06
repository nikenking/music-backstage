package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.Rank;
import com.zhengchuang.music.mapper.RankMapperAnno;
import com.zhengchuang.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评价service实现
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapperAnno mapper;

    /**
     * 增加
     *
     * @param rank
     */
    @Override
    public boolean insert(Rank rank) {
        return mapper.insert(rank)>0;
    }

    /**
     * 查总分
     *
     * @param songListId
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return mapper.selectScoreSum(songListId);
    }

    /**
     * 查总评分人数
     *
     * @param songListId
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return mapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId
     */
    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = mapper.selectRankNum(songListId);
        if(rankNum==0){
            return 5;
        }
        return mapper.selectScoreSum(songListId)/rankNum;
    }
}

package com.renren.x2.feed.jade.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.ShardBy;

import com.renren.x2.feed.beans.TopTenDTO;

@DAO
public interface TopTenJadeDAO {

    final String TABLE_RREFIX = "x2_";

    final String TABLE_TOPTEN = TABLE_RREFIX + "topten";

    final String TABLE_TOPTEN_HISTORY = TABLE_RREFIX + "topten_history";

    final String TOPTEN_COLUMN = "school_id, top_one, top_one_user, top_two, top_two_user, top_three, top_three_user, top_four, top_four_user, top_five, top_five_user, top_six, top_six_user, top_seven, top_seven_user, top_eight, top_eight_user, top_nine, top_nine_user, top_ten, top_ten_user,create_time, update_time, meta_data";

    //获取当期十大IDs
    @SQL("SELECT " + TOPTEN_COLUMN + " FROM " + TABLE_TOPTEN + " WHERE school_id = :1")
    public TopTenDTO getTopTenRecent(int schoolId);

    //存储当期十大IDs
    @SQL("INSERT IGNORE INTO "
            + TABLE_TOPTEN
            + " ("
            + TOPTEN_COLUMN
            + ") VALUES (:2.schoolId, :2.topOne, :2.topOneUser, :2.topTwo, :2.topTwoUser, :2.topThree, :2.topThreeUser, :2.topFour, :2.topFourUser, :2.topFive, :2.topFiveUser, :2.topSix, :2.topSixUser, :2.topSeven, :2.topSevenUser, :2.topEight, :2.topEightUser, :2.topNine, :2.topNineUser, :2.topTen, :2.topTenUser, :2.createTime, :2.updateTime, :2.metaData)")
    public void addTopTenRecent(int schoolId, TopTenDTO topTenDTO);

    //更新当期十大IDs
    @SQL("UPDATE "
            + TABLE_TOPTEN
            + " SET top_one = :2.topOne, top_one_user = :2.topOneUser, top_two = :2.topTwo, top_two_user = :2.topTwoUser, top_three = :2.topThree, top_three_user = :2.topThreeUser, top_four = :2.topFour, top_four_user = :2.topFourUser, top_five = :2.topFive, top_five_user = :2.topFiveUser, top_six = :2.topSix, top_six_user = :2.topSixUser, top_seven = :2.topSeven, top_seven_user = :2.topSevenUser, top_eight = :2.topEight, top_eight_user = :2.topEightUser, top_nine = :2.topNine, top_nine_user = :2.topNineUser, top_ten = :2.topTen, top_ten_user = :2.topTenUser, update_time = :2.updateTime WHERE school_id = :1")
    public void updateTopTenRecent(int schoolId, TopTenDTO topTenDTO);

    //获取所有学校
    @SQL("SELECT school_id FROM " + TABLE_TOPTEN)
    public List<Integer> getSchoolIdList();

    //存储当期十大IDs进入历史十大表
    @SQL("INSERT INTO "
            + TABLE_TOPTEN_HISTORY
            + " ("
            + TOPTEN_COLUMN
            + ") VALUES (:2.schoolId, :2.topOne, :2.topOneUser, :2.topTwo, :2.topTwoUser, :2.topThree, :2.topThreeUser, :2.topFour, :2.topFourUser, :2.topFive, :2.topFiveUser, :2.topSix, :2.topSixUser, :2.topSeven, :2.topSevenUser, :2.topEight, :2.topEightUser, :2.topNine, :2.topNineUser, :2.topTen, :2.topTenUser, :2.createTime, :2.updateTime, :2.metaData)")
    public void addTopTenHistory(@ShardBy int schoolId, TopTenDTO topTenDTO);

    //获取历史十大IDs
    @SQL("SELECT " + TOPTEN_COLUMN + " FROM " + TABLE_TOPTEN_HISTORY
            + " WHERE school_id = :1 ORDER BY create_time DESC LIMIT :2, :3")
    public List<TopTenDTO> getTopTenHistory(@ShardBy int schoolId, int offset, int limit);
}

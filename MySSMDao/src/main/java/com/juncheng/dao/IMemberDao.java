package com.juncheng.dao;

import com.juncheng.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    /**
     * 根据ID查询
     * @return
     */
    @Select("select * from member where id = #{mid}")
    public Member findById(@Param("mid") int id);
}

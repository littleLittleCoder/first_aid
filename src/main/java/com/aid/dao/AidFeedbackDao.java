package com.aid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aid.entity.AidFeedbackDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * (AidFeedback)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@Service
public interface AidFeedbackDao extends BaseMapper<AidFeedbackDO> {

}


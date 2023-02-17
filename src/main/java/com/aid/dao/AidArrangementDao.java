package com.aid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aid.entity.AidArrangementDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * 救援安排表(AidArrangement)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */

public interface AidArrangementDao extends BaseMapper<AidArrangementDO> {

}


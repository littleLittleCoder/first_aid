package com.aid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aid.entity.AidRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * (AidRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-14 13:54:17
 */
@Service
public interface AidRecordDao extends BaseMapper<AidRecordDO> {

}


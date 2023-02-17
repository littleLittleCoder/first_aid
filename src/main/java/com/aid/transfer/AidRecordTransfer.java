package com.aid.transfer;

import com.aid.dto.AidRecordDTO;
import com.aid.entity.AidRecordDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/15 10:48
 * @Description:
 */
public class AidRecordTransfer {


    public static AidRecordDTO transferDoToDto(AidRecordDO aidRecordDO){
        AidRecordDTO aidRecordDTO = new AidRecordDTO();
        if (Objects.isNull(aidRecordDO)){
            return aidRecordDTO;
        }

        BeanUtils.copyProperties(aidRecordDO, aidRecordDTO);
        return aidRecordDTO;
    }


    public static AidRecordDO transferDtoToDo(AidRecordDTO aidRecordDTO){
        AidRecordDO aidRecordDO = new AidRecordDO();
        if (Objects.isNull(aidRecordDTO)){
            return aidRecordDO;
        }

        BeanUtils.copyProperties(aidRecordDTO, aidRecordDO);
        aidRecordDO.setIsActive(true);
        aidRecordDO.setCreatedDate(new Date());
        return aidRecordDO;
    }
}

package com.aid.transfer;

import com.aid.dto.AidFeedbackDTO;
import com.aid.entity.AidFeedbackDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/15 11:41
 * @Description:
 */
public class AidFeedbackTransfer {

    public static AidFeedbackDTO transferDoToDto(AidFeedbackDO aidFeedbackDO){
        AidFeedbackDTO aidFeedbackDTO = new AidFeedbackDTO();
        if (Objects.isNull(aidFeedbackDO)){
            return aidFeedbackDTO;
        }

        BeanUtils.copyProperties(aidFeedbackDO, aidFeedbackDTO);
        return aidFeedbackDTO;
    }


    public static AidFeedbackDO transferDtoToDo(AidFeedbackDTO aidFeedbackDTO){
        AidFeedbackDO aidFeedbackDO = new AidFeedbackDO();
        if (Objects.isNull(aidFeedbackDTO)){
            return aidFeedbackDO;
        }

        BeanUtils.copyProperties(aidFeedbackDTO, aidFeedbackDO);
        aidFeedbackDO.setIsActive( true);
        aidFeedbackDO.setCreatedDate(new Date());
        return aidFeedbackDO;
    }
}

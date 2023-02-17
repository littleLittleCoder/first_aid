package com.aid.transfer;

import com.aid.dto.AidArrangementDTO;
import com.aid.entity.AidArrangementDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/15 11:32
 * @Description:
 */
public class AidArrangementTransfer {

    public static AidArrangementDTO transferDoToDto(AidArrangementDO aidArrangementDO){
        AidArrangementDTO aidArrangementDTO = new AidArrangementDTO();
        if (Objects.isNull(aidArrangementDO)){
            return aidArrangementDTO;
        }

        BeanUtils.copyProperties(aidArrangementDO, aidArrangementDTO);
        return aidArrangementDTO;
    }


    public static AidArrangementDO transferDtoToDo(AidArrangementDTO aidArrangementDTO){
        AidArrangementDO aidArrangementDO = new AidArrangementDO();
        if (Objects.isNull(aidArrangementDTO)){
            return aidArrangementDO;
        }

        BeanUtils.copyProperties(aidArrangementDTO, aidArrangementDO);
        aidArrangementDO.setIsActive(true);
        aidArrangementDO.setCreatedDate(new Date());
        return aidArrangementDO;
    }
}

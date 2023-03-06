package com.aid.param;

import com.aid.dto.RequestList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/17 16:18
 * @Description:
 */
@ApiModel(value = "AidArrangementParam", description = "分页查询条件")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AidArrangementParam extends RequestList {

    private Long id;
    @ApiModelProperty("救援记录表id")
    private Long recordId;
    @ApiModelProperty("调度员id")
    private Long yardmanId;
    @ApiModelProperty("救护车类型")
    private Integer ambulanceType;
    @ApiModelProperty("救护车牌号")
    private String ambulanceNo;
    @ApiModelProperty("出发时间")
    private Date departDate;
    @ApiModelProperty("随行护士集合")
    private String nurseIds;
    @ApiModelProperty("携带设备")
    private String equipment;
}

package com.aid.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/6 14:54
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AidRecordParam", description = "保存条件")
public class AidRecordParam extends RequestList{
    private Long id;
    //病人名称
    @ApiModelProperty("病人名称")
    private String patientName;
    //
    @ApiModelProperty("病发地点")
    private String patientAddress;
    //
    @ApiModelProperty("病人电话")
    private Long patientPhone;
    //
    @ApiModelProperty("病情")
    private String patientCondition;
    //
    @ApiModelProperty("病因")
    private String patientPathogen;
    //
    @ApiModelProperty("接线员id")
    private Long operatorId;

    @ApiModelProperty("创建时间")
    private Date createdDate;

    private Boolean isActive;
}

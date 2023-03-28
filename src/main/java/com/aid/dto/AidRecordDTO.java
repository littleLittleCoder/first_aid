package com.aid.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (AidRecord)表实体类
 *
 * @author makejava
 * @since 2023-02-14 13:54:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AidRecordDTO", description = "分页查询条件")
public class AidRecordDTO  {

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
    private UserDTO operator;

    @ApiModelProperty("创建时间")
    private Date createdDate;

    private Boolean isActive;

}


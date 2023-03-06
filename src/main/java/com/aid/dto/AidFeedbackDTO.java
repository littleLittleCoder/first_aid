package com.aid.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (AidFeedback)表实体类
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AidFeedbackDTO", description = "分页查询条件")
public class AidFeedbackDTO extends RequestList {

    private Long id;
    @ApiModelProperty("记录id")
    private Long recordId;
    //
    @ApiModelProperty("医院名称")
    private String hospitalName;
    //
    @ApiModelProperty("医院地址")
    private String hospitalAddress;
    //
    @ApiModelProperty("送达医院时间")
    private Date hospitalArriveDate;
    //
    @ApiModelProperty("病发地址")
    private String diseaseAddress;
    //
    @ApiModelProperty("病人姓名")
    private String patientName;
    //
    @ApiModelProperty("病人性别")
    private Integer patientSex;
    //
    @ApiModelProperty("病人年龄")
    private Integer patientAge;
    //
    @ApiModelProperty("病人症状")
    private String patientSymptom;
    //
    @ApiModelProperty("病人病史")
    private String patientMedicalHistory;
    //
    @ApiModelProperty("紧急措施")
    private String emergencyMeasures;
    //
    @ApiModelProperty("费用")
    private BigDecimal coastAmount;

    @ApiModelProperty("创建时间")
    private Date createdDate;

}


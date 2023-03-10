package com.aid.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (AidFeedback)表实体类
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@SuppressWarnings("serial")

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("aid_feedback")
public class AidFeedbackDO {

    private Long id;
    //记录id
    private Long recordId;
    //医院名称
    private String hospitalName;
    //医院地址
    private String hospitalAddress;
    //送达医院时间
    private Date hospitalArriveDate;
    //病发地址
    private String diseaseAddress;
    //病人姓名
    private String patientName;
    //病人性别
    private Integer patientSex;
    //病人年龄
    private Integer patientAge;
    //病人症状
    private String patientSymptom;
    //病人病史
    private String patientMedicalHistory;
    //紧急措施
    private String emergencyMeasures;
    //费用
    private BigDecimal coastAmount;

    private Date createdDate;

    private Boolean isActive;

}


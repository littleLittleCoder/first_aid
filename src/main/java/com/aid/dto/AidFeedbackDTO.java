package com.aid.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (AidFeedback)表实体类
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@SuppressWarnings("serial")
public class AidFeedbackDTO extends RequestList {

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

}


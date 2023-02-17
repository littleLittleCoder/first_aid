package com.aid.dto;

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
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AidRecordDTO extends RequestList {

    private Long id;
    //病人名称
    private String patientName;
    //病发地点
    private String patientAddress;
    //病人电话
    private Integer patientPhone;
    //病情
    private String patientCondition;
    //病因
    private String patientPathogen;
    //接线员id
    private Long operatorId;

    private Date createdDate;

    private Boolean isActive;

}


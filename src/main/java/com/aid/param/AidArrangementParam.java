package com.aid.param;

import com.aid.dto.RequestList;

import java.util.Date;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/17 16:18
 * @Description:
 */
public class AidArrangementParam extends RequestList {

    private Long id;
    //救援记录表id
    private Long recordId;
    //调度员id
    private Long yardmanId;
    //救护车类型
    private Integer ambulanceType;
    //救护车牌号
    private String ambulanceNo;
    //出发时间
    private Date departDate;
    //随行护士集合
    private String nurseIds;
    //携带设备
    private String equipment;
}

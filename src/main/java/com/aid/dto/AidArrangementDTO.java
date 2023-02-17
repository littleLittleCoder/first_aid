package com.aid.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 救援安排表(AidArrangement)表实体类
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AidArrangementDTO extends RequestList {

    private Long id;
    //救援记录表id
    private Long recordId;
    //调度员id
    private UserDTO yardmanUser;
    //救护车类型
    private Integer ambulanceType;
    //救护车牌号
    private String ambulanceNo;
    //出发时间
    private Date departDate;
    //随行护士集合
    private List<UserDTO> nurseUsers;
    //携带设备
    private String equipment;

    private Date createdDate;


}


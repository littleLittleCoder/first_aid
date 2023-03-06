package com.aid.dto;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "AidArrangementDTO", description = "分页查询条件")
public class AidArrangementDTO extends RequestList {

    private Long id;
    //
    @ApiModelProperty("救援记录表id")
    private Long recordId;
    //
    @ApiModelProperty("调度员id")
    private UserDTO yardmanUser;
    //
    @ApiModelProperty("救护车类型")
    private Integer ambulanceType;
    //
    @ApiModelProperty("救护车牌号")
    private String ambulanceNo;
    //
    @ApiModelProperty("出发时间")
    private Date departDate;
    //
    @ApiModelProperty("随行护士集合")
    private List<UserDTO> nurseUsers;
    //
    @ApiModelProperty("携带设备")
    private String equipment;

    @ApiModelProperty("创建时间")
    private Date createdDate;


}


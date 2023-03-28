package com.aid.controller;


import com.aid.dto.*;
import com.aid.entity.AidRecordDO;
import com.aid.mapper.AidRecordMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.aid.transfer.AidRecordTransfer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (AidRecord)表控制层
 *
 * @author makejava
 * @since 2023-02-14 13:54:17
 */
@RestController
@RequestMapping("/aidRecord")
@Api(value = "救援记录")
@CrossOrigin(origins = "*")
public class AidRecordController {
    /**
     * 服务对象
     */
    @Resource
    private AidRecordMapper AidRecordMapper;

    @Resource
    private AidRecordTransfer aidRecordTransfer;

    /**
     * 分页查询所有数据
     *
     * @param aidRecord 查询实体
     * @return 所有数据
     */

    @ApiOperation(value = "分页查询", notes = "分页查询", response = AidRecordDTO.class, responseContainer = "List")
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«AidRecordParam»")
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    public Response<ResponseList<AidRecordDTO>> selectAll(@RequestBody Request<AidRecordParam> aidRecord) {
        ResponseList<AidRecordDTO> aidRecordResponseList = new ResponseList<>();
        if (Objects.isNull(aidRecord) || Objects.isNull(aidRecord.getModel())) {
            return Response.successResponse(aidRecordResponseList);
        }

        Page<AidRecordDO> aidRecordPage = new Page<>();
        AidRecordParam model = aidRecord.getModel();
        aidRecordPage.setCurrent(aidRecord.getModel().getPage());
        aidRecordPage.setSize(aidRecord.getModel().getSize());

        QueryWrapper<AidRecordDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(StringUtils.isNotBlank(model.getPatientName()), AidRecordDO::getPatientName, model.getPatientName())
                .eq(Objects.nonNull(model.getPatientPhone()), AidRecordDO::getPatientPhone, model.getPatientPhone())
                .eq(Objects.nonNull(model.getCreatedDate()), AidRecordDO::getCreatedDate, model.getCreatedDate());
        Page<AidRecordDO> result = AidRecordMapper.selectPage(aidRecordPage, wrapper);

        if (Objects.isNull(result) || CollectionUtils.isEmpty(result.getRecords())) {
            return Response.successResponse(aidRecordResponseList);
        }

        aidRecordResponseList.setContent(aidRecordTransfer.transferDoToDto(result.getRecords()));
        aidRecordResponseList.setPage(result.getCurrent());
        aidRecordResponseList.setSize(result.getSize());
        aidRecordResponseList.setTotal(result.getTotal());

        return Response.successResponse(aidRecordResponseList);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */

    @ApiOperation(value = "根据id查询", notes = "根据id查询", response = AidRecordDTO.class)
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«Long»")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public Response<AidRecordDTO> selectOne(@RequestBody Request<Long> param) {
        if (param == null) {
            return Response.errorResponse("参数为空");
        }
        return Response.successResponse(aidRecordTransfer.transferDoToDto(Lists.newArrayList(this.AidRecordMapper.selectById(param.getModel()))).get(0));
    }

    /**
     * 新增数据
     *
     * @param aidRecordDTO 实体对象
     * @return 新增结果
     */

    @ApiOperation(value = "新增", notes = "新增", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "新增", required = true,
            paramType = "body", dataType = "Request«AidRecordParam»")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Boolean> insert(@RequestBody Request<AidRecordParam> aidRecordDTO) {
        return Response.successResponse(this.AidRecordMapper.insert(AidRecordTransfer.transferDtoToDo(aidRecordDTO.getModel())));
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */

    @ApiOperation(value = "更新", notes = "更新", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "更新", required = true,
            paramType = "body", dataType = "Request«AidRecordParam»")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<Boolean> update(@RequestBody Request<AidRecordParam> param) {
        return Response.successResponse(this.AidRecordMapper.updateById(AidRecordTransfer.transferDtoToDo(param.getModel())));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */

    @ApiOperation(value = "删除结果", notes = "删除结果", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "删除结果", required = true,
            paramType = "body", dataType = "Request«List<Long>»")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response<Boolean> delete(@RequestBody Request<List<Long>> param) {
        return Response.successResponse(this.AidRecordMapper.deleteBatchIds(param.getModel()));
    }

    public static void main(String[] args) {
        String a = "{\"page\":100,\"hospitalName\":\"aaa\"}";
        JSONObject jsonObject = JSONObject.parseObject(a);
        AidFeedbackDTO aidFeedbackDTO = jsonObject.toJavaObject(AidFeedbackDTO.class);
        System.out.println(JSON.toJSON(aidFeedbackDTO));
    }
}


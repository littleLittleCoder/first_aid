package com.aid.controller;


import com.aid.dto.*;
import com.aid.entity.AidFeedbackDO;
import com.aid.mapper.AidFeedbackMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.aid.transfer.AidFeedbackTransfer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (AidFeedback)表控制层
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@RestController
@RequestMapping("aidFeedback")
@Api(value = "救援反馈")
@CrossOrigin(origins = "*")
public class AidFeedbackController {
    /**
     * 服务对象
     */
    @Resource
    private AidFeedbackMapper aidFeedbackMapper;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    
    @ApiOperation(value = "分页查询", notes = "分页查询", response = AidFeedbackDTO.class,responseContainer = "List")
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«AidFeedbackDTO»")
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    public Response<ResponseList<AidFeedbackDTO>> selectAll(@RequestBody Request<AidFeedbackDTO> aidFeedback) {
        ResponseList<AidFeedbackDTO> aidRecordResponseList = new ResponseList<>();
        if (Objects.isNull(aidFeedback) || Objects.isNull(aidFeedback.getModel())) {
            return Response.successResponse(aidRecordResponseList);
        }

        AidFeedbackDTO model = aidFeedback.getModel();
        Page<AidFeedbackDO> aidFeedbackPage = new Page<>();
        aidFeedbackPage.setCurrent(model.getPage());
        aidFeedbackPage.setSize(model.getSize());

        QueryWrapper<AidFeedbackDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(StringUtils.isNotBlank(model.getHospitalName()), AidFeedbackDO::getHospitalName, model.getHospitalName())
                .eq(StringUtils.isNotBlank(model.getPatientName()), AidFeedbackDO::getPatientName, model.getPatientName())
                .eq(StringUtils.isNotBlank(model.getPatientName()), AidFeedbackDO::getHospitalName, model.getPatientName());
        Page<AidFeedbackDO> result = aidFeedbackMapper.selectPage(aidFeedbackPage, wrapper);

        if (Objects.isNull(result) || CollectionUtils.isEmpty(result.getRecords())) {
            return Response.successResponse(aidRecordResponseList);
        }

        aidRecordResponseList.setContent(result.getRecords()
                .stream()
                .map(AidFeedbackTransfer::transferDoToDto)
                .collect(Collectors.toList()));
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
    
    @ApiOperation(value = "根据id查询", notes = "根据id查询", response = AidFeedbackDTO.class)
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«Long»")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    public Response<AidFeedbackDTO> selectOne(@RequestBody Request<Long> param) {
        return Response.successResponse(AidFeedbackTransfer.transferDoToDto(this.aidFeedbackMapper.selectById(param.getModel())));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    
    @ApiOperation(value = "新增", notes = "新增", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "新增", required = true,
            paramType = "body", dataType = "Request«AidFeedbackDTO»")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Boolean> insert(@RequestBody Request<AidFeedbackDTO> param) {
        return Response.successResponse(this.aidFeedbackMapper.insert(AidFeedbackTransfer.transferDtoToDo(param.getModel())));
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */
    
    @ApiOperation(value = "更新", notes = "更新", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "更新", required = true,
            paramType = "body", dataType = "Request«AidFeedbackDTO»")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<Boolean> update(@RequestBody Request<AidFeedbackDTO> param) {
        return Response.successResponse(this.aidFeedbackMapper.updateById(AidFeedbackTransfer.transferDtoToDo(param.getModel())));
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
        return Response.successResponse(this.aidFeedbackMapper.deleteBatchIds(param.getModel()));
    }
}


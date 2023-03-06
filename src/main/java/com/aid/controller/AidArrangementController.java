package com.aid.controller;


import com.aid.dto.*;
import com.aid.entity.AidArrangementDO;
import com.aid.entity.AidRecordDO;
import com.aid.mapper.AidArrangementMapper;
import com.aid.mapper.AidRecordMapper;
import com.aid.param.AidArrangementParam;
import com.aid.transfer.AidRecordTransfer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.aid.service.AidArrangementService;
import com.aid.transfer.AidArrangementTransfer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 救援安排表(AidArrangement)表控制层
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@RestController
@RequestMapping("/aidArrangement")
@Api(value = "救援安排操作")
public class AidArrangementController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AidArrangementMapper aidArrangementService;
    @Resource
    private AidArrangementTransfer aidArrangementTransfer;

    /**
     * 分页查询所有数据
     * @return 所有数据
     */
    @CrossOrigin
    @ApiOperation(value = "分页查询", notes = "分页查询", response = AidArrangementDTO.class,responseContainer = "List")
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«AidArrangementParam»")
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    public Response<ResponseList<AidArrangementDTO>> selectPage(@RequestBody Request<AidArrangementParam> aidArrangement) {
        ResponseList<AidArrangementDTO> aidArrangementResponseList = new ResponseList<>();
        if (Objects.isNull(aidArrangement) || Objects.isNull(aidArrangement.getModel())) {
            return Response.successResponse(aidArrangementResponseList);
        }

        Page<AidArrangementDO> aidArrangementPage = new Page<>();
        aidArrangementPage.setCurrent(aidArrangement.getModel().getPage());
        aidArrangementPage.setSize(aidArrangement.getModel().getSize());

        QueryWrapper<AidArrangementDO> wrapper = new QueryWrapper<>();
        Page<AidArrangementDO> result = aidArrangementService.selectPage(aidArrangementPage, wrapper);

        if (Objects.isNull(result) || CollectionUtils.isEmpty(result.getRecords())) {
            return Response.successResponse(aidArrangementResponseList);
        }

        aidArrangementResponseList.setContent(aidArrangementTransfer.transferDoToDto(result.getRecords()));
        aidArrangementResponseList.setPage(result.getCurrent());
        aidArrangementResponseList.setSize(result.getSize());
        aidArrangementResponseList.setTotal(result.getTotal());

        return Response.successResponse(aidArrangementResponseList);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @CrossOrigin
    @ApiOperation(value = "根据id查询", notes = "根据id查询", response = AidArrangementDTO.class)
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«Long»")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    public Response<AidArrangementDTO> selectOne(@RequestBody  Request<Long> request) {
        return Response.successResponse(aidArrangementTransfer.transferDoToDto(Lists.newArrayList(this.aidArrangementService.selectById(request.getModel()))).get(0));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @CrossOrigin
    @ApiOperation(value = "新增", notes = "新增", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "新增", required = true,
            paramType = "body", dataType = "Request«AidArrangementParam»")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Boolean> insert(@RequestBody Request<AidArrangementParam> param) {
        return Response.successResponse(this.aidArrangementService.insert(AidArrangementTransfer.transferParamToDo(param.getModel())));
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */
    @CrossOrigin
    @ApiOperation(value = "更新", notes = "更新", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "更新", required = true,
            paramType = "body", dataType = "Request«AidArrangementParam»")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<Boolean> update(@RequestBody Request<AidArrangementParam> param) {
        return Response.successResponse(this.aidArrangementService.updateById(AidArrangementTransfer.transferParamToDo(param.getModel())));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @CrossOrigin
    @ApiOperation(value = "删除结果", notes = "删除结果", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "删除结果", required = true,
            paramType = "body", dataType = "Request«List<Long>»")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response<Boolean> delete(@RequestBody Request<List<Long>> param) {
        return Response.successResponse(this.aidArrangementService.deleteBatchIds(param.getModel()));
    }
}


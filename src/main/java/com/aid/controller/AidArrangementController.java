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
    @PostMapping("/selectPage")
    public Response<ResponseList<AidArrangementDTO>> selectAll(@RequestBody Request<AidArrangementParam> aidArrangement) {
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
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<AidArrangementDTO> selectOne(@PathVariable Serializable id) {
        return Response.successResponse(aidArrangementTransfer.transferDoToDto(Lists.newArrayList(this.aidArrangementService.selectById(id))).get(0));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @PostMapping("/add")
    public Response<Boolean> insert(@RequestBody AidArrangementParam aidArrangementparam) {
        return Response.successResponse(this.aidArrangementService.insert(AidArrangementTransfer.transferParamToDo(aidArrangementparam)));
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */
    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody AidArrangementParam aidArrangementparam) {
        return Response.successResponse(this.aidArrangementService.updateById(AidArrangementTransfer.transferParamToDo(aidArrangementparam)));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping
    public Response<Boolean> delete(@RequestParam("/delete") List<Long> idList) {
        return Response.successResponse(this.aidArrangementService.deleteBatchIds(idList));
    }
}


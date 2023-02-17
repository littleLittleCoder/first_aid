package com.aid.controller;


import com.aid.dto.*;
import com.aid.entity.AidFeedbackDO;
import com.aid.mapper.AidFeedbackMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @PostMapping("selectPage")
    public Response<ResponseList<AidFeedbackDTO>> selectAll(Request<AidFeedbackDTO> aidFeedback) {
        ResponseList<AidFeedbackDTO> aidRecordResponseList = new ResponseList<>();
        if (Objects.isNull(aidFeedback) || Objects.isNull(aidFeedback.getModel())) {
            return Response.successResponse(aidRecordResponseList);
        }

        Page<AidFeedbackDO> aidFeedbackPage = new Page<>();
        aidFeedbackPage.setCurrent(aidFeedback.getModel().getPage());
        aidFeedbackPage.setSize(aidFeedback.getModel().getSize());

        QueryWrapper<AidFeedbackDO> wrapper = new QueryWrapper<>();
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
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<AidFeedbackDTO> selectOne(@PathVariable Serializable id) {
        return Response.successResponse(AidFeedbackTransfer.transferDoToDto(this.aidFeedbackMapper.selectById(id)));
    }

    /**
     * 新增数据
     *
     * @param aidFeedbackDTO 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Response<Boolean> insert(@RequestBody AidFeedbackDTO aidFeedbackDTO) {
        return Response.successResponse(this.aidFeedbackMapper.insert(AidFeedbackTransfer.transferDtoToDo(aidFeedbackDTO)));
    }

    /**
     * 修改数据
     *
     * @param aidFeedbackDTO 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Response<Boolean> update(@RequestBody AidFeedbackDTO aidFeedbackDTO) {
        return Response.successResponse(this.aidFeedbackMapper.updateById(AidFeedbackTransfer.transferDtoToDo(aidFeedbackDTO)));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Response.successResponse(this.aidFeedbackMapper.deleteBatchIds(idList));
    }
}


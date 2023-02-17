package com.aid.controller;


import com.aid.dto.ResponseList;
import com.aid.entity.AidRecordDO;
import com.aid.mapper.AidRecordMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.aid.dto.AidRecordDTO;
import com.aid.dto.Request;
import com.aid.dto.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class AidRecordController {
    /**
     * 服务对象
     */
    @Resource
    private AidRecordMapper AidRecordMapper;

    /**
     * 分页查询所有数据
     *
     * @param aidRecord 查询实体
     * @return 所有数据
     */
    @PostMapping("/selectPage")
    public Response<ResponseList<AidRecordDTO>> selectAll(Request<AidRecordDTO> aidRecord) {
        ResponseList<AidRecordDTO> aidRecordResponseList = new ResponseList<>();
        if (Objects.isNull(aidRecord) || Objects.isNull(aidRecord.getModel())) {
            return Response.successResponse(aidRecordResponseList);
        }

        Page<AidRecordDO> aidRecordPage = new Page<>();
        aidRecordPage.setCurrent(aidRecord.getModel().getPage());
        aidRecordPage.setSize(aidRecord.getModel().getSize());

        QueryWrapper<AidRecordDO> wrapper = new QueryWrapper<>();
        Page<AidRecordDO> result = AidRecordMapper.selectPage(aidRecordPage, wrapper);

        if (Objects.isNull(result) || CollectionUtils.isEmpty(result.getRecords())) {
            return Response.successResponse(aidRecordResponseList);
        }

        aidRecordResponseList.setContent(result.getRecords()
                .stream()
                .map(AidRecordTransfer::transferDoToDto)
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
    public Response<AidRecordDTO> selectOne(@PathVariable Serializable id) {
        return Response.successResponse(AidRecordTransfer.transferDoToDto(this.AidRecordMapper.selectById(id)));
    }

    /**
     * 新增数据
     *
     * @param aidRecordDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Response<Boolean> insert(@RequestBody Request<AidRecordDTO> aidRecordDTO) {
        return Response.successResponse(this.AidRecordMapper.insert(AidRecordTransfer.transferDtoToDo(aidRecordDTO.getModel())));
    }

    /**
     * 修改数据
     *
     * @param aidRecordDTO 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public Response<Boolean> update(@RequestBody AidRecordDTO aidRecordDTO) {
        return Response.successResponse(this.AidRecordMapper.updateById(AidRecordTransfer.transferDtoToDo(aidRecordDTO)));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Response.successResponse(this.AidRecordMapper.deleteBatchIds(idList));
    }
}


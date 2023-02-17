package com.aid.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.aid.dto.AidFeedbackDTO;
import com.aid.dto.Response;
import org.springframework.web.bind.annotation.*;
import com.aid.service.AidFeedbackService;
import com.aid.transfer.AidFeedbackTransfer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (AidFeedback)表控制层
 *
 * @author makejava
 * @since 2023-02-14 13:54:16
 */
@RestController
@RequestMapping("aidFeedback")
public class AidFeedbackController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AidFeedbackService aidFeedbackService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param aidFeedbackDTO 查询实体
     * @return 所有数据
     */
//    @GetMapping
//    public R selectAll(Page<AidFeedbackDTO> page, AidFeedbackDTO aidFeedbackDTO) {
//        return success(this.aidFeedbackService.page(page, new QueryWrapper<>(aidFeedbackDTO)));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<AidFeedbackDTO> selectOne(@PathVariable Serializable id) {
        return Response.successResponse(AidFeedbackTransfer.transferDoToDto(this.aidFeedbackService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param aidFeedbackDTO 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Response<Boolean> insert(@RequestBody AidFeedbackDTO aidFeedbackDTO) {
        return Response.successResponse(this.aidFeedbackService.save(AidFeedbackTransfer.transferDtoToDo(aidFeedbackDTO)));
    }

    /**
     * 修改数据
     *
     * @param aidFeedbackDTO 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Response<Boolean> update(@RequestBody AidFeedbackDTO aidFeedbackDTO) {
        return Response.successResponse(this.aidFeedbackService.updateById(AidFeedbackTransfer.transferDtoToDo(aidFeedbackDTO)));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Response.successResponse(this.aidFeedbackService.removeByIds(idList));
    }
}


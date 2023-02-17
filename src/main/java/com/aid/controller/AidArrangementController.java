package com.aid.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.aid.dto.AidArrangementDTO;
import com.aid.dto.Response;
import org.springframework.web.bind.annotation.*;
import com.aid.service.AidArrangementService;
import com.aid.transfer.AidArrangementTransfer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

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
    private AidArrangementService aidArrangementService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param aidArrangementDTO 查询实体
     * @return 所有数据
     */
//    @GetMapping("/selectAll")
//    public R selectAll(Page<AidArrangementDTO> page, AidArrangementDTO aidArrangementDTO) {
//        return success(this.aidArrangementService.page(page, new QueryWrapper<>(aidArrangementDTO)));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<AidArrangementDTO> selectOne(@PathVariable Serializable id) {
        return Response.successResponse(AidArrangementTransfer.transferDoToDto(this.aidArrangementService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param aidArrangementDTO 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Response<Boolean> insert(@RequestBody AidArrangementDTO aidArrangementDTO) {
        return Response.successResponse(this.aidArrangementService.save(AidArrangementTransfer.transferDtoToDo(aidArrangementDTO)));
    }

    /**
     * 修改数据
     *
     * @param aidArrangementDTO 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody AidArrangementDTO aidArrangementDTO) {
        return Response.successResponse(this.aidArrangementService.updateById(AidArrangementTransfer.transferDtoToDo(aidArrangementDTO)));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping
    public Response<Boolean> delete(@RequestParam("/delete") List<Long> idList) {
        return Response.successResponse(this.aidArrangementService.removeByIds(idList));
    }
}


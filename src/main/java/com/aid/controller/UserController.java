package com.aid.controller;

import com.aid.dto.*;
import com.aid.entity.AidRecordDO;
import com.aid.entity.UserDO;
import com.aid.mapper.AidRecordMapper;
import com.aid.mapper.UserMapper;
import com.aid.transfer.AidFeedbackTransfer;
import com.aid.transfer.AidRecordTransfer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/9 15:01
 * @Description:
 */

@RestController
@RequestMapping("/userInfo")
@Api(value = "员工信息")
public class UserController {
    @Resource
    private UserMapper userMapper;


    @ApiOperation(value = "根据id查询", notes = "根据id查询", response = UserDO.class, responseContainer = "List")
    @ApiImplicitParam(name = "request", value = "分页查询", required = true,
            paramType = "body", dataType = "Request«UserDO»")
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public Response<List<UserDO>> selectOne() {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserDO::getIsActive, 1);
        return Response.successResponse(userMapper.selectList(wrapper));
    }
}

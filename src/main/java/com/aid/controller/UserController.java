package com.aid.controller;

import cn.hutool.core.bean.BeanUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.ApplicationException;
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


    @ApiOperation(value = "登录并获取身份", notes = "登录并获取身份", response = LoginResDTO.class)
    @ApiImplicitParam(name = "request", value = "登录并获取身份", required = true,
            paramType = "body", dataType = "Request«LoginResDTO»")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<LoginResDTO> login(@RequestBody Request<LoginParam> param) {

        if (param == null
                || BeanUtil.isEmpty(param.getModel())
                || StringUtils.isBlank(param.getModel().getPhone())) {
            return Response.errorResponse("请填写账号");
        }
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserDO::getIsActive, 1)
                .eq(UserDO::getPhone, param.getModel().getPhone());
        UserDO userDO = userMapper.selectOne(wrapper);

        if (BeanUtil.isEmpty(userDO)) {
            return Response.errorResponse("该账号不错在");
        }

        if (!Objects.equals(param.getModel().getPassWorld(), userDO.getPassWorld())) {
            return Response.errorResponse("账号密码错误");
        }

        return Response.successResponse(LoginResDTO.builder().loginResult(true).user(userDO).build());
    }

    @ApiOperation(value = "重置密码", notes = "重置密码", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "重置密码", required = true,
            paramType = "body", dataType = "Request«Boolean»")
    @RequestMapping(value = "/reset/password", method = RequestMethod.POST)
    public Response<Boolean> reset(@RequestBody Request<String> param) {
        if (param == null
                || StringUtils.isBlank(param.getModel())) {
            return Response.errorResponse("请填写账号");
        }

        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(UserDO::getIsActive, 1)
                .eq(UserDO::getPhone, param.getModel());
        UserDO userDO = userMapper.selectOne(wrapper);

        if (BeanUtil.isEmpty(userDO)) {
            return Response.errorResponse("该账号不错在");
        }

        userDO.setPassWorld("123456");
        int i = userMapper.updateById(userDO);
        return Response.successResponse(i > 0);

    }


    @ApiOperation(value = "添加用户", notes = "添加用户", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "添加用户", required = true,
            paramType = "body", dataType = "Request«Boolean»")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Boolean> insert(@RequestBody Request<UserDO> param) {
        if (param == null || param.getModel() == null) {
            return Response.errorResponse("参数为空");
        }
        return Response.successResponse(userMapper.insert(param.getModel()) > 0);
    }


    @ApiOperation(value = "更新用户", notes = "更新用户", response = Boolean.class)
    @ApiImplicitParam(name = "request", value = "更新用户", required = true,
            paramType = "body", dataType = "Request«Boolean»")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<Boolean> update(@RequestBody Request<UserDO> param) {
        if (param == null || param.getModel() == null) {
            return Response.errorResponse("参数为空");
        }
        return Response.successResponse(userMapper.updateById(param.getModel()) > 0);
    }

}

package com.cmpay.yx.controller;

import com.cmpay.framework.data.request.GenericDTO;
import com.cmpay.framework.data.response.GenericRspDTO;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.page.PageInfo;
import com.cmpay.lemon.framework.security.SecurityUtils;
import com.cmpay.lemon.framework.security.UserInfoBase;
import com.cmpay.lemon.framework.utils.IdGenUtils;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.bo.UserQueryBO;
import com.cmpay.yx.dto.*;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yexing
 */
@RestController
@RequestMapping("/v1/ui-template/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/info")
    public GenericRspDTO<UserInfoDTO> getUserInfo(@QueryBody GenericDTO genericDTO) {
        UserInfoBase loginUser = SecurityUtils.getLoginUser();
        UserInfoBO userInfoBO = userService.getUserByUid(Long.valueOf(loginUser.getUserId()));
        UserInfoDTO userInfoDTO = BeanUtils.copyPropertiesReturnDest(new UserInfoDTO(),userInfoBO);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS, userInfoDTO);
    }


    /**
     * 分页查询用户信息
     * @return
     */
    @GetMapping("/list")
    public  GenericRspDTO<UserInfoQueryRspDTO> getUserList(@QueryBody UserInfoQueryReqDTO userInfoDTO){
        //这里是find方法的条件  用以service分页查询
        UserQueryBO userQueryBO = new UserQueryBO();
        userQueryBO.setPageNum(userInfoDTO.getPageNum());
        userQueryBO.setPageSize(userInfoDTO.getPageSize());
        userQueryBO.setUsername(userInfoDTO.getUserName());
        // 新建两个list 分别是DO和存Rsp的数组
        PageInfo<UserDO> userDOPageInfo = userService.selectAllUser(userQueryBO);
        List<UserInfoDTO> dtoList = new ArrayList<>();
        // 脱去敏感信息 转成rsp  rsp里还包含了分页信息
        userDOPageInfo.getList().stream().forEach(item->{
            // 逐步把DO 转换成DTO 放进dtoList里去
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(dto, item);
            dtoList.add(dto);
        });
        // 设定list
        UserInfoQueryRspDTO userRspDTO = new UserInfoQueryRspDTO();

        userRspDTO.setList(dtoList);
        userRspDTO.setPageNum(userDOPageInfo.getPageNum());
        userRspDTO.setPageSize(userDOPageInfo.getPageSize());
        userRspDTO.setPages(userDOPageInfo.getPages());
        userRspDTO.setTotal(userDOPageInfo.getTotal());
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,userRspDTO);
    }

    @PostMapping("/save")
    public DefaultRspDTO<NoBody> insertUser(@RequestBody UserInfoDTO userInfoDTO) {
        // 舟老板写的ID生成器
        Long randomId = Long.valueOf(IdGenUtils.generateId("YX_ID"));

        //转换DTO--BO对象
        UserInfoDTO dto = userInfoDTO;
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, dto);
        bo.setCreateUserNo(Long.valueOf(SecurityUtils.getLoginUserId()));
        bo.setCreateTime(LocalDateTime.now());
        bo.setUpdateUserNo(Long.valueOf(SecurityUtils.getLoginUserId()));
        bo.setUpdateTime(LocalDateTime.now());
        bo.setUid(randomId);
        bo.setRidList(userInfoDTO.getRidList());
        userService.insertUser(bo);
        return DefaultRspDTO.newSuccessInstance();
    }

    @PostMapping("/update")
    public GenericRspDTO<UserInfoRspDTO> updateUser(UserInfoDTO userInfoDTO) {
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, userInfoDTO);
        userService.updateUser(bo);
        UserInfoRspDTO dto = new UserInfoRspDTO();
        BeanUtils.copyProperties(dto,bo);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,dto);
    }

    @GetMapping("/info/{uid}")
    public GenericRspDTO<UserInfoRspDTO> getUserByUid(@PathVariable Long uid) {
        // 调用service的方法  并转换BO
        UserInfoRspDTO dto = new UserInfoRspDTO();
        UserInfoBO bo = userService.getUserByUid(uid);
        BeanUtils.copyProperties(dto, bo);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,dto);
    }

    @DeleteMapping("/delete")
    public DefaultRspDTO<NoBody> deleteUser(@RequestBody UserDeleteReqDTO userDeleteReqDTO) {
        List<Long> uidList = Arrays.asList(userDeleteReqDTO.getUserIds());
        userService.deleteUser(uidList);
        return DefaultRspDTO.newSuccessInstance();
    }

/*    public DefaultRspDTO<NoBody> batchInsertUserRole(UserRoleReqDTO dto){
        UserRoleBO bo = new UserRoleBO();
        BeanUtils.copyProperties(dto, bo);
        // 批量增加成功的个数  应该有一个提示的东西
        int i = userService.batchInsertUserRole(bo);

        return DefaultRspDTO.newSuccessInstance();
    }

    public DefaultRspDTO<NoBody> batchDeleteUserRole(Long uid) {
        int i = userService.batchDeleteUserRole(uid);
        return DefaultRspDTO.newSuccessInstance();
    }*/


}

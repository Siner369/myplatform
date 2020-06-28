package com.cmpay.yx.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.utils.IdGenUtils;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.bo.UserRoleBO;
import com.cmpay.yx.dto.UserInfoDTO;
import com.cmpay.yx.dto.UserInfoQueryRspDTO;
import com.cmpay.yx.dto.UserRoleReqDTO;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yexing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 分页查询用户信息
     * @return
     */
    @GetMapping("/getAllUser")
    public @ResponseBody UserInfoQueryRspDTO selectAllUser(){
        // 新建两个list 分别是DO和存Rsp的数组
        List<UserDO> userDOList = userService.selectAllUser();
        List<UserInfoDTO> dtoList = new ArrayList<>();
        // 脱去敏感信息 转成rsp  rsp里还包含了分页信息
        userDOList.stream().forEach(item->{
            // 逐步把DO 转换成DTO 放进dtoList里去
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(dto, item);
            dtoList.add(dto);
        });
        // 设定list
        UserInfoQueryRspDTO userRspDTO = new UserInfoQueryRspDTO();
        userRspDTO.setList(dtoList);
        return userRspDTO;
    }

    @PostMapping("/insertUser")
    public DefaultRspDTO<NoBody> insertUser( UserInfoDTO userInfoDTO) {
        // 舟老板写的ID生成器
        Long randomId = Long.valueOf(IdGenUtils.generateId("YX_ID"));

        //转换DTO--BO对象
        UserInfoDTO dto = userInfoDTO;
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, dto);
        bo.setUid(randomId);
        userService.insertUser(bo);
        return DefaultRspDTO.newSuccessInstance();
    }


    @PostMapping("/updateUser")
    public DefaultRspDTO<NoBody> updateUser(@RequestBody UserInfoDTO userInfoDTO) {
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, userInfoDTO);
        userService.updateUser(bo);
        return DefaultRspDTO.newSuccessInstance();
    }

    @GetMapping("/getUserByUid/{uid}")
    public @ResponseBody DefaultRspDTO<UserInfoDTO> getUserByUid(@PathVariable Long uid) {
        // 调用service的方法  并转换BO
        UserInfoDTO dto = new UserInfoDTO();
        UserInfoBO bo = userService.getUserByUid(uid);
        BeanUtils.copyProperties(dto, bo);
        return DefaultRspDTO.newSuccessInstance(dto);
    }

    @GetMapping("/deleteUser/{uid}")
    public DefaultRspDTO<NoBody> deleteUser(@PathVariable Long uid) {
        userService.deleteUser(uid);
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

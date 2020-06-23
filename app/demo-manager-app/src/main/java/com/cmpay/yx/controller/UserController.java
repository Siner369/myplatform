package com.cmpay.yx.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.utils.IdGenUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.lemon.framework.page.PageInfo;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.bo.UserRoleBO;
import com.cmpay.yx.dto.UserInfoDTO;
import com.cmpay.yx.dto.UserInfoQueryRspDTO;
import com.cmpay.yx.dto.UserInfoRspDTO;
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

    /**
     * 新增用户
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/insertUser")
    public DefaultRspDTO<NoBody> insertUser( UserInfoDTO userInfoDTO) {
        Long randomId = Long.valueOf(IdGenUtils.generateId("YX_ID"));

        UserInfoDTO dto = userInfoDTO;
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, dto);
        bo.setUid(randomId);
        userService.insertUser(bo);
        return DefaultRspDTO.newSuccessInstance();
    }

    /*@GetMapping("/login")
    public UserInfoRspDTO login(UserInfoDTO dto) {
        // new 一个BO 把 DTO转换成BO  交给service
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(dto, bo);

        // service 把BO 操作一下 交给底层 并返回搜索到的对象
        UserInfoBO login = userService.login(bo);

        // 再new一个 RspDTO  将搜索到的对象转换成RspDTO的格式
        UserInfoRspDTO rspDTO = new UserInfoRspDTO();
        BeanUtils.copyProperties(login,rspDTO);
        return rspDTO;
    }*/

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

    public DefaultRspDTO<NoBody> batchInsertUserRole(UserRoleReqDTO dto){
        UserRoleBO bo = new UserRoleBO();
        BeanUtils.copyProperties(dto, bo);
        // 批量增加成功的个数  应该有一个提示的东西
        int i = userService.batchInsertUserRole(bo);

        return DefaultRspDTO.newSuccessInstance();
    }

    public DefaultRspDTO<NoBody> batchDeleteUserRole(Long uid) {
        int i = userService.batchDeleteUserRole(uid);
        return DefaultRspDTO.newSuccessInstance();
    }


}

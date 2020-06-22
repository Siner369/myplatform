package com.cmpay.yx.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.utils.IdGenUtils;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.dto.UserInfoDTO;
import com.cmpay.yx.dto.UserInfoQueryRspDTO;
import com.cmpay.yx.dto.UserInfoRspDTO;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



    @GetMapping("/getAllUser")
    public List<UserInfoQueryRspDTO> selectAllUser(){
        // 新建两个list 分别是DO和RSP的
        List<UserInfoQueryRspDTO> rspDTOList = new ArrayList<>();
        List<UserDO> userDOList = userService.selectAllUser();

        // 脱去敏感信息 转成rsp  rsp里还包含了分页信息
        userDOList.stream().forEach(item->{
            UserInfoQueryRspDTO userRspDTO = new UserInfoQueryRspDTO();
            BeanUtils.copyProperties(userRspDTO, item);
            rspDTOList.add(userRspDTO);
        });
        System.out.println(rspDTOList);
        return rspDTOList;
    }

    @PostMapping("/inserUser")
    public int inserUser(UserInfoDTO userInfoDTO) {
        String uid = IdGenUtils.generateCommonId("999999999");

        UserInfoDTO dto = userInfoDTO;
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(bo, dto);
        bo.setUid(Long.valueOf(uid));
        int i = userService.insertUser(bo);
        return i;
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
    public UserInfoRspDTO updateUser(UserInfoDTO dto) {
        return null;
    }
    
}

package com.cmpay.yx.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.dto.RoleDTO;
import com.cmpay.yx.dto.RoleQueryRspDTO;
import com.cmpay.yx.entity.RoleDO;
import com.cmpay.yx.service.RoleService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yexing
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/getAllRole")
    public @ResponseBody RoleQueryRspDTO selectAllUser(){
        // 新建两个list 分别是DO和存Rsp的数组
        List<RoleDO> roleDOList = roleService.selectAllRole();
        List<RoleDTO> dtoList = new ArrayList<>();

        roleDOList.stream().forEach(item->{
        // 逐步把DO 转换成DTO 放进dtoList里去
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(dto, item);
        dtoList.add(dto);
        });

        // 前台列表显示
        RoleQueryRspDTO roleQueryRspDTO = new RoleQueryRspDTO();
        roleQueryRspDTO.setList(dtoList);
        return roleQueryRspDTO;
    }


    @PostMapping("/insertRole")
    public DefaultRspDTO<NoBody> insertRole(@RequestBody RoleDTO roleDTO) {
        // 随机生成rid, ID生成器在UserController使用过
        Long randomId = RandomUtils.nextLong();

        RoleDTO dto = roleDTO;
        RoleBO bo = new RoleBO();
        // 这里是把role的信息还有 菜单的midList传下去了，真正复杂的对象转换在service完成
        BeanUtils.copyProperties(bo,dto);
        bo.setRid(randomId);

        roleService.insertRole(bo);
        return DefaultRspDTO.newSuccessInstance();
    }


    @PostMapping("/updateRole")
    public DefaultRspDTO<NoBody> updateUser(@RequestBody RoleDTO roleDTO) {
        RoleDTO dto = roleDTO;
        RoleBO bo = new RoleBO();
        // 这里的bo装的也是单纯的权限列表mid 的list 不是RoleMenu对象list
        BeanUtils.copyProperties(bo, roleDTO);
        roleService.updateRole(bo);
        return DefaultRspDTO.newSuccessInstance();
    }

    @GetMapping("/getUserByRid/{rid}")
    public @ResponseBody DefaultRspDTO<RoleDTO> getUserByRid(@PathVariable Long rid) {
        // 调用service的方法  并转换BO
        RoleDTO dto = new RoleDTO();

        //TODO：搜索数据库这个role的权限，把能使用的权限菜单id 组成一个list加载BO上
        RoleBO bo = roleService.getRoleByRid(rid);
        BeanUtils.copyProperties(dto, bo);
        return DefaultRspDTO.newSuccessInstance(dto);
    }

    @GetMapping("/deleteRole/{rid}")
    public DefaultRspDTO<NoBody> deleteMenu(@PathVariable Long rid) {
        //TODO:service层要手动“级联”删除 一下 角色菜单表里 与这个rid相关联的数据（假删除）
        roleService.deleteRole(rid);
        return DefaultRspDTO.newSuccessInstance();
    }

    /////

}

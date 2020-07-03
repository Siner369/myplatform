package com.cmpay.yx.controller;

import com.cmpay.framework.data.response.GenericRspDTO;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.lemon.framework.page.PageInfo;
import com.cmpay.lemon.framework.security.SecurityUtils;
import com.cmpay.yx.bo.MenuBO;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.bo.UserQueryBO;
import com.cmpay.yx.dto.*;
import com.cmpay.yx.entity.MenuDO;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.MenuService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yexing
 */
@RestController
@RequestMapping("/v1/ui-template/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/select")
    public GenericRspDTO<MenuQueryRspDTO> getMenuList(){
        List<MenuDTO> menuDTO = menuService.selectAllMenu();
        // 设定list
        MenuQueryRspDTO menuQueryRspDTO = new MenuQueryRspDTO();
        menuQueryRspDTO.setList(menuDTO);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,menuQueryRspDTO);
    }

    @GetMapping("/selectAll")
    public GenericRspDTO<MenuQueryRspDTO> getMenuNoOrder(){
        List<MenuDTO> menuDTOList = menuService.getMenuExceptButton();
        // 设定list
        MenuQueryRspDTO menuQueryRspDTO = new MenuQueryRspDTO();
        menuQueryRspDTO.setList(menuDTOList);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,menuQueryRspDTO);
    }

    @PostMapping("/save")
    public GenericRspDTO<NoBody> insertMenu(@RequestBody MenuDTO menuDTO) {
        // 随机生成rid, ID生成器在UserController使用过
        Long randomId = Long.valueOf(RandomUtils.nextInt());

        MenuDTO dto = menuDTO;
        MenuBO bo = new MenuBO();
        // 转换类型 交给service
        BeanUtils.copyProperties(bo,dto);
        bo.setMid(randomId);
        bo.setCreateUserNo(Long.valueOf(SecurityUtils.getLoginUserId()));
        bo.setUpdateUserNo(Long.valueOf(SecurityUtils.getLoginUserId()));
        bo.setCreateTime(LocalDateTime.now());
        bo.setUpdateTime(LocalDateTime.now());
        menuService.insertMenu(bo);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS);
    }

    @DeleteMapping("/delete/{mid}")
    public GenericRspDTO<NoBody> deleteMenu(@PathVariable Long mid) {
        // 假删除
        menuService.deleteMenu(mid);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS);
    }

    @GetMapping("/info/{mid}")
    public GenericRspDTO<MenuRspDTO> getMenuByMid(@PathVariable Long mid) {
        // 假删除
        MenuBO menuByMid = menuService.getMenuByMid(mid);
        MenuRspDTO menuRspDTO = new MenuRspDTO();
        BeanUtils.copyProperties(menuRspDTO,menuByMid);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS,menuRspDTO);
    }


    @PostMapping("/update")
    public GenericRspDTO<NoBody> updateMenu(@RequestBody MenuDTO menuDTO) {
        MenuDTO dto = menuDTO;
        MenuBO bo = new MenuBO();
        // 转换类型 交给service
        BeanUtils.copyProperties(bo, dto);
        menuService.updateMenu(bo);
        return GenericRspDTO.newInstance(MsgEnum.SUCCESS);
    }



}

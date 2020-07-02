package com.cmpay.yx.controller;

import com.cmpay.framework.data.response.GenericRspDTO;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.lemon.framework.page.PageInfo;
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

    @PostMapping("/insertMenu")
    public DefaultRspDTO<NoBody> insertMenu(@RequestBody MenuDTO menuDTO) {
        // 随机生成rid, ID生成器在UserController使用过
        Long randomId = RandomUtils.nextLong();

        MenuDTO dto = menuDTO;
        MenuBO bo = new MenuBO();
        // 转换类型 交给service
        BeanUtils.copyProperties(bo,dto);

        menuService.insertMenu(bo);
        return DefaultRspDTO.newSuccessInstance();
    }

    @GetMapping("/deleteMenu/{mid}")
    public DefaultRspDTO<NoBody> deleteMenu(@PathVariable Long mid) {
        // 假删除
        menuService.deleteMenu(mid);
        return DefaultRspDTO.newSuccessInstance();
    }


    @PostMapping("/updateMenu")
    public DefaultRspDTO<NoBody> updateMenu(@RequestBody MenuDTO menuDTO) {
        MenuDTO dto = menuDTO;
        MenuBO bo = new MenuBO();
        // 转换类型 交给service
        BeanUtils.copyProperties(bo, dto);
        menuService.updateMenu(bo);
        return DefaultRspDTO.newSuccessInstance();
    }



}

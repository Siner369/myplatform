package com.cmpay.yx.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.data.NoBody;
import com.cmpay.yx.bo.MenuBO;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.dto.MenuDTO;
import com.cmpay.yx.dto.RoleDTO;
import com.cmpay.yx.service.MenuService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yexing
 */
@RestController
@RequestMapping("/v1/ui-template/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

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

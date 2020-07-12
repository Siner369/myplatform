package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.yx.bo.MenuBO;
import com.cmpay.yx.dao.IMenuDao;
import com.cmpay.yx.dao.IRoleMenuDao;
import com.cmpay.yx.dto.MenuDTO;
import com.cmpay.yx.entity.MenuDO;
import com.cmpay.yx.entity.RoleMenuDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexing
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private IMenuDao menuDao;

    @Resource
    private IRoleMenuDao roleMenuDao;

    @Override
    public List selectAllMenu() {
        List<MenuDO> menuList = menuDao.selectAllMenu();
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (MenuDO menuDO : menuList){
            MenuDTO menuInfoDTO = new MenuDTO();
            BeanUtils.copy(menuInfoDTO,menuDO);
            menuDTOList.add(menuInfoDTO);
        }
        return buildMenu(menuDTOList,0L);
    }

    public List<Map> buildMenu(List<MenuDTO> menuList, Long parentId){
        List<Map> mapList = new ArrayList<>();
        for (MenuDTO menu : menuList){
            if (parentId.equals(menu.getSuperMenuId())){

                Map map = new HashMap(16);
                map.put("id",menu.getMid());
                map.put("menuName",menu.getMenuName());
                map.put("menuType",menu.getMenuType());
                map.put("authMark",menu.getAuthMark());
                map.put("pid",menu.getSuperMenuId());
                map.put("menuEnglishName",menu.getMenuEnglishName());
                map.put("menuLink",menu.getMenuLink());
                map.put("redirect",menu.getRedirect());
                map.put("children",buildMenu(menuList,menu.getMid()));
                mapList.add(map);
            }
        }
        return mapList;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertMenu(MenuBO menuBO) {
        MenuDO menuDO = new MenuDO();
        MenuBO bo = menuBO;
        BeanUtils.copyProperties(menuDO,bo);
        menuDO.setIsUse(true);
        int i = menuDao.insertMenu(menuDO);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }
    }

    @Override
    public MenuBO getMenuByMid(Long mid) {
        MenuDO menuByMid = menuDao.getMenuByMid(mid);
        MenuBO menuBO = new MenuBO();
        BeanUtils.copyProperties(menuBO,menuByMid);
        return menuBO;
    }

    @Override
    public void updateMenu(MenuBO menuBO) {
        MenuDO menuDO = new MenuDO();
        BeanUtils.copyProperties(menuDO,menuBO);
        int i = menuDao.updateMenu(menuDO);
        if (i==0){
            BusinessException.throwBusinessException(MsgEnum.DB_UPDATE_FAILED);
        }
    }

    @Override
    public void deleteMenu(Long mid) {
        int i = menuDao.deleteMenu(mid);
        int j = roleMenuDao.deleteRoleMenuByMid(mid+"");

        if (i==0){
            BusinessException.throwBusinessException(MsgEnum.DB_DELETE_FAILED);
        }
    }

    @Override
    public List<MenuDTO> getMenuExceptButton() {
        List<MenuDO> menuDOList = menuDao.selectAllMenu();
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (MenuDO menuDO : menuDOList){
            if (!"button".equals(menuDO.getMenuType())){
                MenuDTO menuInfoDTO = new MenuDTO();
                BeanUtils.copy(menuInfoDTO,menuDO);
                menuDTOList.add(menuInfoDTO);
            }
        }
        return menuDTOList;
    }


}

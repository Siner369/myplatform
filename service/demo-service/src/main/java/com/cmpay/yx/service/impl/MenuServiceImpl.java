package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.yx.bo.MenuBO;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.bo.RoleMenuBO;
import com.cmpay.yx.dao.IMenuDao;
import com.cmpay.yx.dao.IRoleMenuDao;
import com.cmpay.yx.entity.MenuDO;
import com.cmpay.yx.entity.RoleDO;
import com.cmpay.yx.entity.RoleMenuDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yexing
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private IMenuDao menuDao;

    @Override
    public List<MenuDO> selectAllMenu() {
        return menuDao.selectAllMenu();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertMenu(MenuBO menuBO) {
        MenuDO menuDO = new MenuDO();
        MenuBO bo = menuBO;
        BeanUtils.copyProperties(menuDO,bo);
        menuDO.setCreateTime(LocalDateTime.now());
        menuDO.setUpdateTime(LocalDateTime.now());
        int i = menuDao.insertMenu(menuDO);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }
    }

    @Override
    public MenuBO getMenuByMid(Long mid) {
        return null;
    }

    @Override
    public void updateMenu(MenuBO menuBO) {

    }

    @Override
    public void deleteMenu(Long mid) {

    }



}

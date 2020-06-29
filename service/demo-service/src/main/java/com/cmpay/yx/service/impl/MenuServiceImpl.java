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

    //=================递归查询所有菜单=================




   /* private List<MenuDTO> buildMenu(List<MenuDTO> menuList) {
        //最终返回的list集合
        List<MenuDTO> finalNode = new ArrayList<>();
        for (MenuDTO menuInfoNode : menuList){
            //得到顶层pid=0的递归入口
            if(menuInfoNode.getPid().equals(0L)){
                //顶层等级为1
                menuInfoNode.setLevel(1);
                //根据顶层，逐渐向下查询其子菜单，封装到finalNode
                finalNode.add(selectChildren(menuInfoNode,menuList));
            }
        }
        return finalNode;
    }*/


   /* private MenuInfoDTO selectChildren(MenuInfoDTO menuInfoNode, List<MenuInfoDTO> menuList) {
        //因为向一层里面放二层，二层放三层，把对象初始话  防止出现空指针异常
        menuInfoNode.setChildren(new ArrayList<>());
        for(MenuInfoDTO it : menuList){
            //判断id和pid是否相等
            if(menuInfoNode.getId().equals(it.getPid())){
                //如果相等 子菜单等级等于父菜单等级+1
                int level = menuInfoNode.getLevel()+1;
                it.setLevel(level);
                //所有children为空，进行初始话操作
                if(menuInfoNode.getChildren() == null){
                    menuInfoNode.setChildren(new ArrayList<MenuInfoDTO>());
                }
                //把查询出来的子菜单放到父菜单中
                menuInfoNode.getChildren().add(selectChildren(it,menuList));
            }
        }
        return menuInfoNode;
    }*/
    // 递归查询完

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

package com.cmpay.yx.entity;

import com.cmpay.lemon.framework.annotation.PreInsert;
import com.cmpay.lemon.framework.annotation.PreUpdate;
import com.cmpay.lemon.framework.data.DOBasicOperation;

import java.time.LocalDateTime;

/**
 * 时间戳
 *
 * @author : Noone
 */
public class BaseDO implements DOBasicOperation {

    private String tmSmp;

    @PreInsert
    @Override
    public void preInsert() {
        this.setTmSmp(LocalDateTime.now().toString());
    }

    @PreUpdate
    @Override
    public void preUpdate() {
        this.setTmSmp(LocalDateTime.now().toString());
    }

    public String getTmSmp() {
        return this.tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp;
    }
}

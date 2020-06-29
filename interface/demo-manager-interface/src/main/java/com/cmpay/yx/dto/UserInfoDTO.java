package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Administrator
 */
@Data
@ToString
public class UserInfoDTO {

    private Long uid;
    /**
     * @Fields username 用户名
     */
    private String username;
    /**
     * @Fields password 密码
     */
    private String password;
    /**
     * @Fields name 姓名
     */
    private String name;
    /**
     * @Fields email 邮箱
     */
    private String email;
    /**
     * @Fields mobile 手机号
     */
    private String mobile;
    /**
     * @Fields state 禁用 或者 正常
     */
    private Byte state;
    /**
     * @Fields createUserNo 创建人
     */
    private Long createUserNo;
    /**
     * @Fields createTime 创建时间
     */
    private LocalDateTime createTime;
    /**
     * @Fields updateUserNo 更新人
     */
    private Long updateUserNo;
    /**
     * @Fields updateTime 创建时间
     */
    private LocalDateTime updateTime;
    /**
     * @Fields ridList 这个用户所拥有的角色id的List
     */
    private List<Long> ridList;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getCreateUserNo() {
        return createUserNo;
    }

    public void setCreateUserNo(Long createUserNo) {
        this.createUserNo = createUserNo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserNo() {
        return updateUserNo;
    }

    public void setUpdateUserNo(Long updateUserNo) {
        this.updateUserNo = updateUserNo;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<Long> getRidList() {
        return ridList;
    }

    public void setRidList(List<Long> ridList) {
        this.ridList = ridList;
    }
}

package com.panshi.springbootmybatisdemo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Items
 * @Description
 * @Author guolongfei
 * @Date 2020/4/25  11:35
 * @Version
 */
public class Items implements Serializable {

    private static final long serialVersionUID = -2076689976276924256L;
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String context;
    private BigDecimal price;
    private String img;
    private Integer skuId;
    private Long createTime;
    private Long endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}

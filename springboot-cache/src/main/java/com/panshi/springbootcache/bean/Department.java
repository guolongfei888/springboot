package com.panshi.springbootcache.bean;

import java.io.Serializable;

/**
 * @ClassName Department
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  17:26
 * @Version
 */
public class Department implements Serializable {


    private static final long serialVersionUID = -6405443964046652382L;
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

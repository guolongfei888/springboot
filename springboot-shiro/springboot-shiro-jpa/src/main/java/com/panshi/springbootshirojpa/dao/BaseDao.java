package com.panshi.springbootshirojpa.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @ClassName BaseDao
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:55
 * @Version
 */
@NoRepositoryBean
public interface BaseDao<T, I extends Serializable>
        extends PagingAndSortingRepository<T, I>, JpaSpecificationExecutor<T> {
}

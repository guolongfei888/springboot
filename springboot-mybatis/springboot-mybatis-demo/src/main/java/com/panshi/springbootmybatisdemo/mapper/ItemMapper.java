package com.panshi.springbootmybatisdemo.mapper;

import com.panshi.springbootmybatisdemo.model.Items;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName ItemMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/4/25  11:42
 * @Version
 */
@Mapper
public interface ItemMapper {
    @Insert("insert into items values(null,#{username},#{password},#{age},#{context},#{price},#{createTime},#{endTime},#{img},#{skuId})")
    void insertItem(Items items);

    @Select("select * from items")
    Items selectItemById(Integer id);
}

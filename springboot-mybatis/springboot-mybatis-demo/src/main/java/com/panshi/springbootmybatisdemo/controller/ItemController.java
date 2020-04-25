package com.panshi.springbootmybatisdemo.controller;



import com.panshi.springbootmybatisdemo.mapper.ItemMapper;
import com.panshi.springbootmybatisdemo.model.Items;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName ItemController
 * @Description
 * @Author guolongfei
 * @Date 2020/4/25  14:01
 * @Version
 */
@RestController
public class ItemController {
    @Resource
    private ItemMapper itemMapper;

    /**
     *  用insert 插入大量数据太慢
     */
    @Transactional
    @RequestMapping("/insert/item")
    public void insertItem() {
        Items item = new Items();
        for (int i=0;i<100000;i++) {

            item.setUsername("zhangsan "+i);
            item.setPassword("123456 "+i);
            item.setAge(i);
            item.setContext("这是一个订单信息"+i);
            item.setPrice(new BigDecimal(""+i));

            item.setImg("classpath:jvm.jpg");
            item.setSkuId(i);
            item.setCreateTime(System.currentTimeMillis());
            item.setEndTime(System.currentTimeMillis());
            itemMapper.insertItem(item);
        }
    }

    @RequestMapping("/get/item")
    public Items getItemById(Integer id) {
        return itemMapper.selectItemById(id);
    }
}

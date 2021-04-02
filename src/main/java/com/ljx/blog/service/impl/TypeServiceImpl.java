package com.ljx.blog.service.impl;

import com.ljx.blog.dao.TypeDao;
import com.ljx.blog.entity.Type;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 分类业务层接口实现类
 * @author Lin
 */
@SuppressWarnings("AlibabaTransactionMustHaveRollback")
@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeDao typeDao;

    /**
     * @Description 保存分类
     * @return  返回执行结果
     */
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    /**
     * @Description 按照id查询分类
     * @param id  要查询的分类的id
     */
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    /**
     * @Description 查询所有分类
     */
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    /**
     * @param name  要查询的分类的名字
     * @return  返回查询结果
     */
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    /**
     * @Description 更新、修改分类
     * @param type  要更新的分类
     * @return  更新结果
     */
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    /**
     * @Description 按照id删除分类
     * @param id  要删除的分类的id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    /**
     * @Description 前台查询所有分类
     */
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }
}

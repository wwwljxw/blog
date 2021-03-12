package com.ljx.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.Mapper.TTypeMapper;
import com.ljx.blog.pojo.TType;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TTypeMapper tTypeMapper;

    /**
     * 分页查询
     * @param page 页数
     * @param offset  条数
     * @return  查询结果
     */
    @Override
    public PageInfo<TType> findAllByPage(int page, int offset) {
        PageHelper.startPage(page,offset);
        List<TType> typePage = tTypeMapper.selectByList();
        return  new PageInfo<TType>(typePage);
    }

    @Override
    public TType selectById(long id) {
        return tTypeMapper.selectById(id);
    }

    @Override
    public TType selectByName(String name) {
        return tTypeMapper.selectByNameAfter(name);
    }

    @Override
    public int insert(String name) {
        return tTypeMapper.insert(name);
    }

    @Override
    public int updateById(String name, long id) {
        return tTypeMapper.updateById(name,id);
    }

    @Override
    public int deleteById(long id) {
        return tTypeMapper.deleteById(id);
    }
}

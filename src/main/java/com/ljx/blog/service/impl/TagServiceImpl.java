package com.ljx.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.Mapper.TTagMapper;
import com.ljx.blog.pojo.TTag;
import com.ljx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TTagMapper tTagMapper;

    @Override
    public List<TTag> getAllTag() {
        return tTagMapper.selectByList();
    }

    @Override
    public PageInfo<TTag> findAllByPage(int page, int offset) {
        Page<Object> objects = PageHelper.startPage(page, offset);
        List<TTag> tTags = tTagMapper.selectByList();
        return new PageInfo<TTag>(tTags);
    }

    @Override
    public TTag selectById(long id) {
        return tTagMapper.selectById(id);
    }

    @Override
    public TTag selectByName(String name) {
        return tTagMapper.selectByNameAfter(name);
    }

    @Override
    public int insert(String name) {
        return tTagMapper.insert(name);
    }

    @Override
    public int updateById(String name, long id) {
        return tTagMapper.updateById(name,id);
    }

    @Override
    public int deleteById(long id) {
        return tTagMapper.deleteById(id);
    }
}

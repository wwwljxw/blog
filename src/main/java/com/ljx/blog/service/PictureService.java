package com.ljx.blog.service;

import com.ljx.blog.entity.Picture;

import java.util.List;

/**
 * @Description 照片墙业务层接口
 * @author Lin
 */
public interface PictureService {

    List<Picture> listPicture();

    int savePicture(Picture picture);

    Picture getPicture(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);
}

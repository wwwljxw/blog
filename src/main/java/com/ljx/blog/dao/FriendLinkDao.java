package com.ljx.blog.dao;


import com.ljx.blog.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 友链持久层接口
 */
@Repository
public interface FriendLinkDao {

    List<FriendLink> listFriendLink();

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLink(Long id);

    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);

}
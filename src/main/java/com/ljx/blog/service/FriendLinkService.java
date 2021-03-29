package com.ljx.blog.service;

import com.ljx.blog.entity.FriendLink;

import java.util.List;

/**
 * @Description 友链业务层接口
 * @author Lin
 */
public interface FriendLinkService {

    List<FriendLink> listFriendLink();

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    FriendLink getFriendLink(Long id);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);
}

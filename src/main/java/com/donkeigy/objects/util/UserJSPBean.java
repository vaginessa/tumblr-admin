package com.donkeigy.objects.util;

import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.User;

import java.util.List;

/**
 * Created by cedric on 2/27/15.
 */
public class UserJSPBean extends User
{
    private final List<Blog> blogs;
    private final String name;
    private final Boolean following;
    private final Integer likes;

    public UserJSPBean(User user)
    {
        this.blogs = user.getBlogs();
        this.name = user.getName();
        this.following = user.isFollowing();
         this.likes = user.getLikeCount();

    }

    @Override
    public List<Blog> getBlogs() {
        return blogs;
    }

    @Override
    public String getName() {
        return name;
    }

    public Boolean getFollowing() {
        return following;
    }

    public Integer getLikes() {
        return likes;
    }
}

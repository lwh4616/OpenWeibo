package com.weihui.openweibo.entity.db;


import com.weihui.openweibo.entity.db.BaseEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User extends BaseEntity {

    @Id(autoincrement = false)
    public Long id;
    public String screen_name;
    public String name;
    public String province;
    public String city;
    public String location;
    public String description;
    public String url;
    public String profile_image_url;
    public String domain;
    public String gender;
    public long followers_count;
    public long friends_count;
    public long statuses_count;
    public int favourites_count;
    public String created_at;
    public boolean following;
    public boolean allow_all_act_msg;
    public String remark;
    public boolean geo_enabled;
    public boolean allow_all_comment;
    public String avatar_large;
    public boolean verified;
    public String verified_reason;
    public boolean follow_me;
    public int online_status;
    public int bi_followers_count;
    @Generated(hash = 522591453)
    public User(Long id, String screen_name, String name, String province,
            String city, String location, String description, String url,
            String profile_image_url, String domain, String gender,
            long followers_count, long friends_count, long statuses_count,
            int favourites_count, String created_at, boolean following,
            boolean allow_all_act_msg, String remark, boolean geo_enabled,
            boolean allow_all_comment, String avatar_large, boolean verified,
            String verified_reason, boolean follow_me, int online_status,
            int bi_followers_count) {
        this.id = id;
        this.screen_name = screen_name;
        this.name = name;
        this.province = province;
        this.city = city;
        this.location = location;
        this.description = description;
        this.url = url;
        this.profile_image_url = profile_image_url;
        this.domain = domain;
        this.gender = gender;
        this.followers_count = followers_count;
        this.friends_count = friends_count;
        this.statuses_count = statuses_count;
        this.favourites_count = favourites_count;
        this.created_at = created_at;
        this.following = following;
        this.allow_all_act_msg = allow_all_act_msg;
        this.remark = remark;
        this.geo_enabled = geo_enabled;
        this.allow_all_comment = allow_all_comment;
        this.avatar_large = avatar_large;
        this.verified = verified;
        this.verified_reason = verified_reason;
        this.follow_me = follow_me;
        this.online_status = online_status;
        this.bi_followers_count = bi_followers_count;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getScreen_name() {
        return this.screen_name;
    }
    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getProfile_image_url() {
        return this.profile_image_url;
    }
    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
    public String getDomain() {
        return this.domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public long getFollowers_count() {
        return this.followers_count;
    }
    public void setFollowers_count(long followers_count) {
        this.followers_count = followers_count;
    }
    public long getFriends_count() {
        return this.friends_count;
    }
    public void setFriends_count(long friends_count) {
        this.friends_count = friends_count;
    }
    public long getStatuses_count() {
        return this.statuses_count;
    }
    public void setStatuses_count(long statuses_count) {
        this.statuses_count = statuses_count;
    }
    public int getFavourites_count() {
        return this.favourites_count;
    }
    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }
    public String getCreated_at() {
        return this.created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public boolean getFollowing() {
        return this.following;
    }
    public void setFollowing(boolean following) {
        this.following = following;
    }
    public boolean getAllow_all_act_msg() {
        return this.allow_all_act_msg;
    }
    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public boolean getGeo_enabled() {
        return this.geo_enabled;
    }
    public void setGeo_enabled(boolean geo_enabled) {
        this.geo_enabled = geo_enabled;
    }
    public boolean getAllow_all_comment() {
        return this.allow_all_comment;
    }
    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }
    public String getAvatar_large() {
        return this.avatar_large;
    }
    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }
    public boolean getVerified() {
        return this.verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public String getVerified_reason() {
        return this.verified_reason;
    }
    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }
    public boolean getFollow_me() {
        return this.follow_me;
    }
    public void setFollow_me(boolean follow_me) {
        this.follow_me = follow_me;
    }
    public int getOnline_status() {
        return this.online_status;
    }
    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }
    public int getBi_followers_count() {
        return this.bi_followers_count;
    }
    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

package com.weihui.openweibo.entity.db;


import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;

import com.weihui.openweibo.BR;
import com.weihui.openweibo.util.TimeFormatHelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by weihui on 2018/4/9.
 */

@Entity(nameInDb = "Status")
public class Status extends BaseEntity {

    private String created_at;

    @Id(autoincrement = false)
    private Long id;

    private long mid;

    private String idstr;

    private String text;

    private String source;

    public Long timeLineId;


    @Bindable
    private boolean favorited;

    private boolean truncated;

    private String thumbnail_pic;

    private String bmiddle_pic;

    private String original_pic;

    private int reposts_count;

    private int comments_count;

    private int attitudes_count;

    @Bindable
    @ToOne(joinProperty = "userId")
    public User user;

    private long userId;

    private String favorited_time;

    private String fromSource = "";

    @Transient
    @Bindable
    private String formatTime;


    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1443046080)
    private transient StatusDao myDao;

    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;

    @Generated(hash = 1212246547)
    public Status(String created_at, Long id, long mid, String idstr, String text, String source, Long timeLineId,
            boolean favorited, boolean truncated, String thumbnail_pic, String bmiddle_pic, String original_pic,
            int reposts_count, int comments_count, int attitudes_count, long userId, String favorited_time,
            String fromSource) {
        this.created_at = created_at;
        this.id = id;
        this.mid = mid;
        this.idstr = idstr;
        this.text = text;
        this.source = source;
        this.timeLineId = timeLineId;
        this.favorited = favorited;
        this.truncated = truncated;
        this.thumbnail_pic = thumbnail_pic;
        this.bmiddle_pic = bmiddle_pic;
        this.original_pic = original_pic;
        this.reposts_count = reposts_count;
        this.comments_count = comments_count;
        this.attitudes_count = attitudes_count;
        this.userId = userId;
        this.favorited_time = favorited_time;
        this.fromSource = fromSource;
    }


    @Generated(hash = 1855832530)
    public Status() {
    }

    public void formatTime() {
        if (TextUtils.isEmpty(created_at)) {
            return;
        }
        formatTime = TimeFormatHelper.format(created_at);

        notifyPropertyChanged(BR.formatTime);
    }


    public void decodeSource() {
        if (!TextUtils.isEmpty(fromSource) || TextUtils.isEmpty(source)) {
            return;
        }
        this.fromSource = "来自 " + source.substring(source.indexOf(">") + 1, source.indexOf("</"));
        Log.d("TAG", this.fromSource + ":" + this.source);
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
        notifyPropertyChanged(BR.favorited);
    }


    public String getCreated_at() {
        return this.created_at;
    }


    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public long getMid() {
        return this.mid;
    }


    public void setMid(long mid) {
        this.mid = mid;
    }


    public String getIdstr() {
        return this.idstr;
    }


    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }


    public String getText() {
        return this.text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getSource() {
        return this.source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public boolean getFavorited() {
        return this.favorited;
    }


    public boolean getTruncated() {
        return this.truncated;
    }


    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }


    public String getThumbnail_pic() {
        return this.thumbnail_pic;
    }


    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }


    public String getBmiddle_pic() {
        return this.bmiddle_pic;
    }


    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }


    public String getOriginal_pic() {
        return this.original_pic;
    }


    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }


    public int getReposts_count() {
        return this.reposts_count;
    }


    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }


    public int getComments_count() {
        return this.comments_count;
    }


    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }


    public int getAttitudes_count() {
        return this.attitudes_count;
    }


    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }


    public String getFavorited_time() {
        return this.favorited_time;
    }


    public void setFavorited_time(String favorited_time) {
        this.favorited_time = favorited_time;
    }


    public String getFromSource() {
        return this.fromSource;
    }


    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }


    public String getFormatTime() {
        return this.formatTime;
    }


    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }


    public void prepareWithTimeLine(TimeLine timeLine) {
        formatTime();
        decodeSource();
        timeLineId = timeLine.getId();
    }


    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 115391908)
    public User getUser() {
        long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }


    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 462495677)
    public void setUser(@NotNull User user) {
        if (user == null) {
            throw new DaoException("To-one property 'userId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            userId = user.getId();
            user__resolvedKey = userId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    public void setId(Long id) {
        this.id = id;
    }


    public long getUserId() {
        return this.userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getTimeLineId() {
        return this.timeLineId;
    }


    public void setTimeLineId(long timeLineId) {
        this.timeLineId = timeLineId;
    }


    public void setTimeLineId(Long timeLineId) {
        this.timeLineId = timeLineId;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1377533788)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStatusDao() : null;
    }
}

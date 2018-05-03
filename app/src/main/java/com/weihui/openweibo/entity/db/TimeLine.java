package com.weihui.openweibo.entity.db;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by weihui on 2018/4/9.
 */
@Entity()
public class TimeLine {


    @OrderBy("id DESC")
    @ToMany(referencedJoinProperty = "timeLineId")
    public List<Status> statuses;

    @Id(autoincrement = false)
    public Long id;

    public boolean hasvisible;

    public long previous_cursor;

    public long next_cursor;

    public int total_number;

    public int has_unread;

    //下一组time line的最大id
    public long max_id;

    public long interval;

    //当条time line 最大id,也就是最晚的微博
    public long since_id;//

    @Transient
    public boolean saveDb = true;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 149733589)
    private transient TimeLineDao myDao;


    @Generated(hash = 355075207)
    public TimeLine() {
    }


    @Generated(hash = 1406695052)
    public TimeLine(Long id, boolean hasvisible, long previous_cursor, long next_cursor,
            int total_number, int has_unread, long max_id, long interval, long since_id) {
        this.id = id;
        this.hasvisible = hasvisible;
        this.previous_cursor = previous_cursor;
        this.next_cursor = next_cursor;
        this.total_number = total_number;
        this.has_unread = has_unread;
        this.max_id = max_id;
        this.interval = interval;
        this.since_id = since_id;
    }


    public boolean getHasvisible() {
        return this.hasvisible;
    }

    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public long getPrevious_cursor() {
        return this.previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return this.next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public int getTotal_number() {
        return this.total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public int getHas_unread() {
        return this.has_unread;
    }

    public void setHas_unread(int has_unread) {
        this.has_unread = has_unread;
    }

    public long getMax_id() {
        return this.max_id;
    }

    public void setMax_id(long max_id) {
        this.max_id = max_id;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }



    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1349251417)
    public List<Status> getStatuses() {
        if (statuses == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StatusDao targetDao = daoSession.getStatusDao();
            List<Status> statusesNew = targetDao._queryTimeLine_Statuses(id);
            synchronized (this) {
                if (statuses == null) {
                    statuses = statusesNew;
                }
            }
        }
        return statuses;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 834591518)
    public synchronized void resetStatuses() {
        statuses = null;
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

    public Long getId() {
        return this.id;
    }

  

    public long getSince_id() {
        return this.since_id;
    }


    public void setSince_id(long since_id) {
        this.since_id = since_id;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1798727257)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTimeLineDao() : null;
    }


}

package com.coral.foundation.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class JPABaseEntity implements BaseEntity {
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_CREATTIME")
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_LASTMODIFIEDTIME")
    private Date lastModifiedTime;
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return lastModifiedTime;
    }

    public void setUpdateTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
    
    @PrePersist
    protected void prePersist() {
        Date currentTime = new Date();
        setCreateTime(currentTime);
        setUpdateTime(currentTime);
    }
    
    @PreUpdate
    protected void preUpdate() {
        Date currentTime = new Date();
        setUpdateTime(currentTime);
    }    
}


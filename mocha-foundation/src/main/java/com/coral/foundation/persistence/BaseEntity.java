package com.coral.foundation.persistence;

import java.util.Date;

public interface BaseEntity {
    
    public Date getCreateTime();
    public void setCreateTime(Date createTime);
    public Date getUpdateTime();
    public void setUpdateTime(Date lastModifiedTime);    
    public Long getID();
}

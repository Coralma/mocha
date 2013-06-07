package com.coral.foundation.security.basic.dao;
import java.util.Collection;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
 * MochaReportDao is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public interface MochaReportDao extends Dao<MochaReport> {

	public void saveMochaReport(MochaReport mochaReport);

	public Collection executeReport(MochaReport mochaReport);

}

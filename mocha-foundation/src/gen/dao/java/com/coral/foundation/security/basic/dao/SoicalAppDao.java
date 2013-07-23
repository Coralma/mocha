package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * SoicalAppDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface SoicalAppDao extends Dao<SoicalApp> {
	public SoicalApp findSoicaAppByRequestToken(String requestToken);
}


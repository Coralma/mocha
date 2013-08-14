package com.coral.foundation.md;

import java.util.List;
import java.util.Properties;

import com.coral.foundation.md.model.Mocha;

public interface IHbmGenerator {

	public void generater(List<Mocha> coral, Properties properties) throws Exception;
}

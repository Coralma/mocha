/**
 * 
 */
package com.coral.foundation.md;

import java.util.List;

import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Resource;

/**
 * @author Coral.Ma
 *
 */
public interface IEntityXMLPaser {

	public List<Mocha> parseMappingXml(Resource resource) throws Exception;
}

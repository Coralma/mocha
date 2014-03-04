package com.mocha.cooperate.restfulservice;

import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.codehaus.jackson.xc.XmlAdapterJsonDeserializer;

import com.mocha.service.model.UserFeedType;
import com.mocha.service.model.UserFeeds;

public abstract class ServiceDomainAdapter<T,B> extends XmlAdapter {
	
	
}

package com.mocha.cooperate.restfulservice;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MochaRestfulService extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MochaRestfulService() {
		register(MoxyJsonFeature.class);
		register(RestFulServiceImpl.class);
	}
}

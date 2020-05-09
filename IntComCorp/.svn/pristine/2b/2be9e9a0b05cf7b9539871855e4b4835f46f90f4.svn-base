package com.intcomcorp.intcomcorpApplication.service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.ZabbixRequest;

public interface ZabbixApi {

	void init();

	void destroy();

	JSONObject call(ZabbixRequest request);

	boolean login(String user, String password);

}

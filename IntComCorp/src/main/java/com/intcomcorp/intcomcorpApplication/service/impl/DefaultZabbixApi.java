package com.intcomcorp.intcomcorpApplication.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.ZabbixRequest;
import com.intcomcorp.intcomcorpApplication.service.ZabbixApi;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

public class DefaultZabbixApi implements ZabbixApi {
	private static final Logger logger = LoggerFactory.getLogger(DefaultZabbixApi.class);

	private CloseableHttpClient httpClient;
	private URI uri;

	private volatile String auth;

	/**
	 * 
	 * @param url
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public DefaultZabbixApi(String url) throws KeyManagementException, NoSuchAlgorithmException {
		try {
			uri = new URI(url.trim());
		} catch (URISyntaxException e) {
			throw new RuntimeException("url invalid", e);
		}
	}

	/**
	 * 
	 * @param uri
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public DefaultZabbixApi(URI uri) throws KeyManagementException, NoSuchAlgorithmException {
		this.uri = uri;
	}

	/**
	 * 
	 * @param url
	 * @param httpClient
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public DefaultZabbixApi(String url, CloseableHttpClient httpClient)
			throws KeyManagementException, NoSuchAlgorithmException {
		this(url);
		this.httpClient = httpClient;
	}

	/**
	 * 
	 * @param uri
	 * @param httpClient
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public DefaultZabbixApi(URI uri, CloseableHttpClient httpClient)
			throws KeyManagementException, NoSuchAlgorithmException {
		this(uri);
		this.httpClient = httpClient;
	}

	@Override
	public void init() {
		if (httpClient == null) {
			httpClient = HttpClients.custom().build();
		}
	}

	@Override
	public void destroy() {
		if (httpClient != null) {
			try {
				httpClient.close();
			} catch (Exception e) {
				logger.error("close httpclient error!", e);
			}
		}
	}

	@Override
	public boolean login(String user, String password) {
		this.auth = null;
		Request request = RequestBuilder.newBuilder().paramEntry("user", user).paramEntry("password", password)
				.method("user.login").build();
		JSONObject response = call(request);
		String auth = response.getString("result");
		if (auth != null && !auth.isEmpty()) {
			this.auth = auth;
			return true;
		}
		return false;
	}



	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean hostExists(String name) {
		Request request = RequestBuilder.newBuilder().method("host.exists").paramEntry("name", name).build();
		JSONObject response = call(request);
		return response.getBooleanValue("result");
	}

	/**
	 * 
	 * @param host
	 * @param groupId
	 * @return
	 */
	public String hostCreate(String host, String groupId) {
		JSONArray groups = new JSONArray();
		JSONObject group = new JSONObject();
		group.put("groupid", groupId);
		groups.add(group);
		Request request = RequestBuilder.newBuilder().method("host.create").paramEntry("host", host)
				.paramEntry("groups", groups).build();
		JSONObject response = call(request);
		return response.getJSONObject("result").getJSONArray("hostids").getString(0);
	}

	/**
	 * 
	 * @param hosts
	 * @return
	 */
	public String deleteHosts(String[] hosts) {
		Request request = RequestBuilder.newBuilder().method("host.delete").paramEntry("params", hosts).build();
		JSONObject response = call(request);
		return response.getJSONObject("result").getJSONArray("hostids").getString(0);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean hostgroupExists(String name) {
		Request request = RequestBuilder.newBuilder().method("hostgroup.exists").paramEntry("name", name).build();
		JSONObject response = call(request);
		return response.getBooleanValue("result");
	}

	/**
	 * @param name
	 * @return groupId
	 */
	public String hostgroupCreate(String name) {
		Request request = RequestBuilder.newBuilder().method("hostgroup.create").paramEntry("name", name).build();
		JSONObject response = call(request);
		return response.getJSONObject("result").getJSONArray("groupids").getString(0);
	}

	@Override
	public JSONObject call(ZabbixRequest request) {
		if (request.getAuth() == null) {
			request.setAuth(this.auth);
		}
		try {
			HttpUriRequest httpRequest = org.apache.http.client.methods.RequestBuilder.post().setUri(uri)
					.addHeader("Content-Type", "application/json")
					.setEntity(new StringEntity(JSON.toJSONString(request), ContentType.APPLICATION_JSON)).build();
			CloseableHttpResponse response = httpClient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			byte[] data = EntityUtils.toByteArray(entity);
			return (JSONObject) JSON.parse(data);
		} catch (IOException e) {
			throw new RuntimeException("DefaultZabbixApi call exception!", e);
		}
	}

}

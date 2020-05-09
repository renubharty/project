package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.response.UserGet;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixUserService {

	@Autowired
	private ZabbixInitializerService initializerService;

	public List<UserGet> get() throws JSONException {
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.get").paramEntry("output", "extend").build();
			JSONObject jsonObject = new JSONObject(initializerService.zabbixApi.call(request).toString());
			JSONArray resultData = jsonObject.getJSONArray("result");
			List<UserGet> listofUser = new ArrayList<>();
			for (int i = 0; i < resultData.length(); i++) {
				JSONObject userGetData = resultData.getJSONObject(i);
				UserGet userGet = new UserGet();
				userGet.setUserId(userGetData.getString("userid"));
				userGet.setFirstName(userGetData.getString("alias"));
				userGet.setLastName(userGetData.getString("surname"));
				listofUser.add(userGet);
			}

			return listofUser;

		} else {
			return null;
		}
	}

	public void updateUser(UserGet userGet) throws JSONException {
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.update")
					.paramEntry("userid", userGet.getUserId()).paramEntry("name", userGet.getFirstName())
					.paramEntry("surname", userGet.getLastName()).build();
			initializerService.zabbixApi.call(request);
		}

	}

}

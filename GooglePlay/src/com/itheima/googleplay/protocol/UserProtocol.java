package com.itheima.googleplay.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.googleplay.domain.UserInfo;

public class UserProtocol extends BaseProtocol<UserInfo> {

	@Override
	public UserInfo paserJson(String json) {
		//"{name:'´«ÖÇ»Æ¸Ç',email:'huanggai@itcast.cn',url:'image/user.png'}"
		try {
			JSONObject jsonObject=new JSONObject(json);
			String name=jsonObject.getString("name");
			String email=jsonObject.getString("email");
			String url=jsonObject.getString("url");
			UserInfo userInfo=new UserInfo(name, url, email);
			return userInfo;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getKey() {
		return "user";
	}

}

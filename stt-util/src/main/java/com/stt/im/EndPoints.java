package com.stt.im;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;

/**
 * JerseyWebTarget EndPoints
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface EndPoints {

	final JerseyClient CLIENT = JerseyUtils.getJerseyClient(true);

	final JerseyWebTarget ROOT_TARGET = CLIENT
			.target(Constants.API_HTTP_SCHEMA + "://"
					+ Constants.API_SERVER_HOST + "/");

	JerseyWebTarget APPLICATION_TEMPLATE = ROOT_TARGET
			.path("{org_name}").path("{app_name}");

	JerseyWebTarget TOKEN_APP_TARGET = APPLICATION_TEMPLATE
			.path("token");

	JerseyWebTarget USERS_TARGET = APPLICATION_TEMPLATE.path("users");

	JerseyWebTarget USERS_STATUS = APPLICATION_TEMPLATE.path("users").path("{ownerUserName}").path("status");

	JerseyWebTarget USERS_ADDFRIENDS_TARGET = APPLICATION_TEMPLATE
			.path("users").path("{ownerUserName}").path("contacts")
			.path("users").path("{friendUserName}");

	JerseyWebTarget MESSAGES_TARGET = APPLICATION_TEMPLATE
			.path("messages");

	JerseyWebTarget CHATMESSAGES_TARGET = APPLICATION_TEMPLATE
			.path("chatmessages");

	JerseyWebTarget EXPORTCHATMESSAGES_TARGET = APPLICATION_TEMPLATE
			.path("chatmessages").path("{time}");

	JerseyWebTarget CHATGROUPS_TARGET = APPLICATION_TEMPLATE
			.path("chatgroups");

	/**
	 * 分页获取群组成员
	 */
	JerseyWebTarget GROUP_USERS_TARGET = CHATGROUPS_TARGET
			.path("{group_id}").path("users");
	/**
	 * 添加群组成员[单个]
	 */
	JerseyWebTarget ADD_GROUPUSER_TARGET = CHATGROUPS_TARGET
			.path("{group_id}").path("users").path("{username}");

	JerseyWebTarget CHATFILES_TARGET = APPLICATION_TEMPLATE
			.path("chatfiles");
}

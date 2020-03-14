package in.co.hubapp.mobile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.service.EventServiceMob;
import in.co.hubapp.mobile.types.RequestType;
import in.co.hubapp.model.Events;

@RestController
@RequestMapping(RequestType.API + RequestType.EVENT)
public class EventController {

	@Autowired
	EventServiceMob eventServiceMob;

	@RequestMapping(value = RequestType.USER_EVENT, method = RequestMethod.POST, consumes = { "application/json",
			"multipart/form-data" })
	public HubGenRes Events(@RequestBody Events req) {

		HubGenRes res = eventServiceMob.postEvents(req);

		return res;
	}

	@RequestMapping(value = RequestType.USER_EVENT, method = RequestMethod.GET, produces = { "application/json",
			"multipart/form-data" })
	public List<EventRes> getEventsById(@RequestBody HubGenReq req) throws IOException {

		List<EventRes> res = eventServiceMob.getEventByUserId(req);

		return res;
	}

}

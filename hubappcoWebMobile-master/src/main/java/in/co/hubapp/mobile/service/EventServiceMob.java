package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;
import java.util.List;


import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.model.Events;

public interface EventServiceMob {

	public HubGenRes postEvents(Events req);

	public List<EventRes> getEventByUserId(HubGenReq req) throws FileNotFoundException;

}

package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;

import in.co.hubapp.mobile.channel.EventReq;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;

public interface EventServiceMob {

	public HubGenRes postEvents(EventReq req);

	public HubGenRes getEventByUserId(HubGenReq req) throws FileNotFoundException;

}

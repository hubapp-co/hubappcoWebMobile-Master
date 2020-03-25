package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.EventReq;
import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.repository.DocumentRepositoryMob;
import in.co.hubapp.mobile.repository.EventRepositoryMob;
import in.co.hubapp.mobile.util.Document;
import in.co.hubapp.model.Events;

@Service
public class EventServiceMobImpl implements EventServiceMob {

	@Autowired
	EventRepositoryMob eventRepositoryMob;

	@Autowired
	DocumentRepositoryMob documentRepositoryMob;

	@Override
	public HubGenRes postEvents(EventReq req) {
		HubGenRes res = new HubGenRes();
		Events newEvent = new Events();
		if (req != null) {
			if (req.getEventName() != null) {
				newEvent.setEventName(req.getEventName());
			}
			if (req.getEventDecription() != null) {
				newEvent.setEventDecription(req.getEventDecription());
			}
			if (req.getLikes() != null) {
				newEvent.setLikes(req.getLikes());
			}
			if (req.getEventUserId() != null) {
				newEvent.setEventUserId(req.getEventUserId());
			}
			if (req.getEventDate() != null) {
				newEvent.setEventDate(req.getEventDate());
			}
			if (req.getEvenTime() != null) {
				newEvent.setEvenTime(req.getEvenTime());
			}
			if (req.getMemberName() != null) {
				newEvent.setMemberName(req.getMemberName());
			}
			if (req.getEventImgId() != null) {

				newEvent.setEventImgId(req.getEventImgId());
			}

			try {

				eventRepositoryMob.save(newEvent);

				res.setMessage("event saved Successfully");
				res.setStatus("Success");
				return res;
			} catch (Exception e) {
				res.setMessage("unsuccessfull in saving event");
				res.setStatus("Failure");
				return res;
			}
		}

		res.setMessage("Enter the details");
		res.setStatus("Failure");
		return res;

	}

	@Override
	public HubGenRes getEventByUserId(HubGenReq req) throws FileNotFoundException {

		HubGenRes res = new HubGenRes();
		List<Events> allEvents = new ArrayList<>();
		List<EventRes> eventRes = new ArrayList<>();
		Document doc = new Document();

		if (req.getSecureKey() != null) {
			try {
				allEvents = eventRepositoryMob.findEventById(req.getSecureKey());
			} catch (Exception e) {
				return res;

			}
			if (allEvents != null) {
				for (Events events : allEvents) {
					EventRes addEvent = new EventRes();
					if (events.getEventName() != null) {
						addEvent.setEventName(events.getEventName());
					}
					if (events.getEventDecription() != null) {
						addEvent.setEventDecription(events.getEventDecription());
					}
					if (events.getLikes() != null) {
						addEvent.setLikes(events.getLikes());
					}
					if (events.getEventImgId() != null) {
						try {
							doc = documentRepositoryMob.findDocumentById(events.getEventImgId());
							addEvent.setEventImgUrl(doc.getFilePath());
						}

						catch (Exception e) {

							res.setStatus("Failure");
							res.setMessage("Not able to fetch Document Details");
							return res;
						}

					}
					if (events.getEventDate() != null) {
						addEvent.setEventDate(events.getEventDate());
					}
					if (events.getEvenTime() != null) {
						addEvent.setEvenTime(events.getEvenTime());
					}

					if (events.getEventUserId() != null) {

						addEvent.setEventUserId(events.getEventUserId());
					}

					eventRes.add(addEvent);

				}

			}
			res.setStatus("Success");
			res.setMessage("Events for this user");
			res.setEvents(eventRes);
			return res;

		}

		res.setStatus("Failure");
		res.setMessage("Please enter the post userID");
		return res;
	}

}

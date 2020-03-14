package in.co.hubapp.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.repository.EventRepositoryMob;
import in.co.hubapp.model.Events;

@Service
public class EventServiceMobImpl implements EventServiceMob {

	@Autowired
	EventRepositoryMob eventRepositoryMob;

	@Override
	public HubGenRes postEvents(Events req) {
		String finalFilePath = null;
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
			if (req.getEventImageInBytes() != null) {

				try {

					finalFilePath = writeByteToFile(req.getEventImageInBytes());

				} catch (IOException e) {
					res.setMessage("Error uploading image");
					res.setStatus("Failure");
					return res;
				}

				newEvent.setEventImg(finalFilePath);

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

	private static byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}

	private static String writeByteToFile(byte[] bytes) throws IOException {
		LocalDateTime current = LocalDateTime.now();
		String filePath = null;
		final String dir = System.getProperty("user.dir") + "/uploads/";
		File convertFile = new File(dir + current);
		convertFile.createNewFile();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(convertFile);
			fos.write(bytes);
			fos.close();
			filePath = convertFile.getAbsolutePath();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return filePath;
	}

	@Override
	public List<EventRes> getEventByUserId(HubGenReq req) throws FileNotFoundException {

		List<EventRes> res = new ArrayList<>();
		List<Events> allEvents = new ArrayList<>();
		EventRes addEvent = new EventRes();

		if (req.getSecureKey() != null) {
			try {
				allEvents = (List<Events>) eventRepositoryMob.findEventById(req.getSecureKey());
			} catch (Exception e) {
				return res;

			}

			if (allEvents != null) {
				for (Events events : allEvents) {

					if (events.getEventName() != null) {
						addEvent.setEventName(events.getEventName());
					}
					if (events.getEventDecription() != null) {
						addEvent.setEventDecription(events.getEventDecription());
					}
					if (events.getLikes() != null) {
						addEvent.setLikes(events.getLikes());
					}
					if (events.getEventImg() != null) {
						File convertFile = new File(events.getEventImg());
						byte[] bArray = readFileToByteArray(convertFile);

						addEvent.setEventImageInBytes(bArray);
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

					res.add(addEvent);

				}

			}
			return res;

		}

		return res;
	}

}

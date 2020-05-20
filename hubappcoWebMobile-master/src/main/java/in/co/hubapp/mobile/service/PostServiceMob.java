package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;
import java.util.List;

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.model.Posts;

public interface PostServiceMob {

	public HubGenRes post(PostReq post);

	public HubGenRes getPostByUserId(HubGenReq req) throws FileNotFoundException;

	public List<Posts> getPostsByUserId(Long id);

}

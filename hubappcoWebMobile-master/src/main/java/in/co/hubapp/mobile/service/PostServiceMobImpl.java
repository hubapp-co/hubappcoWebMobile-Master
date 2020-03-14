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

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.repository.PostsRepositoryMob;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.model.Posts;
import in.co.hubapp.model.User;

@Service
public class PostServiceMobImpl implements PostServiceMob {

	@Autowired
	PostsRepositoryMob postsRepositoryMob;

	@Autowired
	UserRepositoryMob userRepositoryMob;

	@Override
	public HubGenRes post(PostReq post) {

		HubGenRes res = new HubGenRes();
		Posts newpost = new Posts();
		String filePath;
		if (post != null) {
			if (post.getPostTitle() != null) {
				newpost.setPostTitle(post.getPostTitle());
			}
			if (post.getPostDescription() != null) {
				newpost.setPostDescription(post.getPostDescription());
			}
			if (post.getLikes() != null) {
				newpost.setLikes(post.getLikes());
			}
			if (post.getPostUserId() != null) {
				User userpost = userRepositoryMob.getOne(post.getPostUserId());
				newpost.setPostUserId(userpost);
			}
			if (post.getPostImage() != null) {

				LocalDateTime current = LocalDateTime.now();

				try {
//					File convertFile = new File("/home/rajesh/Desktop/" + current);
//					File convertFile = new File("/public_html/Deployment/Deployed/uploads" + current);
					final String dir = System.getProperty("user.dir") + "/uploads/";
					File convertFile = new File(dir + current);
					convertFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convertFile);
					fos.write(post.getPostImage());
					fos.close();
					filePath = convertFile.getAbsolutePath();
				} catch (IOException e) {
					res.setMessage("Error uploading image");
					res.setStatus("Failure");
					return res;
				}
				newpost.setPostImageUrl(filePath);

			}

			try {

				postsRepositoryMob.save(newpost);

				res.setMessage("post saved Successfully");
				res.setStatus("Success");
				return res;
			} catch (Exception e) {
				res.setMessage("unsuccessfull in saving post");
				res.setStatus("Failure");
				return res;
			}
		}

		res.setMessage("Enter the details");
		res.setStatus("Failure");
		return res;

	}

	public List<PostRes> getPostByUserId(HubGenReq req) throws FileNotFoundException {

		List<PostRes> res = new ArrayList<>();
		List<Posts> allPosts = new ArrayList<>();
		PostRes addPost = new PostRes();

		if (req.getSecureKey() != null) {
			try {
				allPosts = (List<Posts>) postsRepositoryMob.findByPostByUSer(req.getSecureKey());
			} catch (Exception e) {
				return res;

			}

			if (allPosts != null) {
				for (Posts posts : allPosts) {

					if (posts.getPostTitle() != null) {
						addPost.setPostTitle(posts.getPostTitle());
					}
					if (posts.getPostDescription() != null) {
						addPost.setPostDescription(posts.getPostDescription());
					}
					if (posts.getLikes() != null) {
						addPost.setLikes(posts.getLikes());
					}
					if (posts.getPostImageUrl() != null) {
						File convertFile = new File(posts.getPostImageUrl());
						byte[] bArray = readFileToByteArray(convertFile);

						addPost.setPostImage(bArray);
					}
					if (posts.getCategoryId() != null) {

						addPost.setCategory(posts.getCategoryId());
					}

					if (posts.getCategoryChildId() != null) {
						addPost.setCategoryChild(posts.getCategoryChildId());
					}

					if (posts.getCategorySubChildId() != null) {
						addPost.setCategorySubChild(posts.getCategorySubChildId());
					}

					res.add(addPost);

				}

			}
			return res;

		}

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

}

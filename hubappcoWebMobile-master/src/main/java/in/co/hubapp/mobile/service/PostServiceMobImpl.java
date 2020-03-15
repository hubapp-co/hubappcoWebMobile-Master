package in.co.hubapp.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.repository.CategoryChildRepositoryMob;
import in.co.hubapp.mobile.repository.CategoryRepositoryMob;
import in.co.hubapp.mobile.repository.CategorySubChildRepositoryMob;
import in.co.hubapp.mobile.repository.DocumentRepositoryMob;
import in.co.hubapp.mobile.repository.PostsRepositoryMob;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.mobile.util.Document;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.CategorySubChild;
import in.co.hubapp.model.Posts;
import in.co.hubapp.model.User;

@Service
public class PostServiceMobImpl implements PostServiceMob {

	@Autowired
	PostsRepositoryMob postsRepositoryMob;

	@Autowired
	UserRepositoryMob userRepositoryMob;

	@Autowired
	CategoryRepositoryMob categoryRepositoryMob;

	@Autowired
	CategoryChildRepositoryMob categoryChildRepositoryMob;

	@Autowired
	CategorySubChildRepositoryMob categorySubChildRepositoryMob;

	@Autowired
	DocumentRepositoryMob documentRepositoryMob;

	@Override
	public HubGenRes post(PostReq post) {

		HubGenRes res = new HubGenRes();
		Posts newpost = new Posts();
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
				newpost.setPostUserId(post.getPostUserId());
			}
			if (post.getDocId() != null) {

				/*
				 * LocalDateTime current = LocalDateTime.now();
				 * 
				 * try { // File convertFile = new File("/home/rajesh/Desktop/" + current); //
				 * File convertFile = new File("/public_html/Deployment/Deployed/uploads" +
				 * current); final String dir = System.getProperty("user.dir") + "/uploads/";
				 * File convertFile = new File(dir + current); convertFile.createNewFile();
				 * FileOutputStream fos = new FileOutputStream(convertFile);
				 * fos.write(post.getPostImage()); fos.close(); filePath =
				 * convertFile.getAbsolutePath(); } catch (IOException e) {
				 * res.setMessage("Error uploading image"); res.setStatus("Failure"); return
				 * res; }
				 */
				newpost.setImageId(post.getDocId());
			}
			if (post.getCategoryId() != null) {
				newpost.setCategoryId(post.getCategoryId());
			}

			if (post.getCategoryChildId() != null) {
				newpost.setCategoryChildId(post.getCategoryChildId());

			}
			if (post.getCategorySubChildId() != null) {
				newpost.setCategorySubChildId(post.getCategorySubChildId());

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

	public HubGenRes getPostByUserId(HubGenReq req) throws FileNotFoundException {

		HubGenRes res = new HubGenRes();
		List<PostRes> postRes = new ArrayList<>();
		List<Posts> allPosts = new ArrayList<>();
		PostRes addPost = new PostRes();
		Document doc = new Document();

		if (req.getSecureKey() != null) {
			try {
				allPosts = (List<Posts>) postsRepositoryMob.findByPostByUSer(req.getSecureKey());
			} catch (Exception e) {
				res.setMessage("Failure");
				res.setMessage("Not able to fetch post details");
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
					if (posts.getImageId() != null) {
						try {
							doc = documentRepositoryMob.findDocumentById(posts.getImageId());
							addPost.setPostImageUrl(doc.getFilePath());
						}

						catch (Exception e) {

							res.setStatus("Failure");
							res.setMessage("Not able to fetch Document Details");
							return res;
						}
					}
					try {
						if (posts.getCategoryId() != null) {
							Category cat = categoryRepositoryMob.findByCategoryId(posts.getCategoryId());
							addPost.setCategoryName(cat.getCategoryName());
						}

						if (posts.getCategoryChildId() != null) {

							CategoryChild child = categoryChildRepositoryMob
									.findOneChildById(posts.getCategoryChildId());
							addPost.setCategoryChildName(child.getCategoryChildName());
						}

						if (posts.getCategorySubChildId() != null) {

							CategorySubChild subChild = categorySubChildRepositoryMob
									.findOneSubChildById(posts.getCategorySubChildId());
							addPost.setCategorySubChildName(subChild.getCategorySubChildName());
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					postRes.add(addPost);

				}

				res.setStatus("Success");
				res.setMessage("POst details of the user");
				res.setPosts(postRes);
				return res;

			}
			res.setStatus("Failure");
			res.setMessage("No Posts for this user");
			return res;

		}

		res.setStatus("Failure");
		res.setMessage("Please enter the post userID");
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

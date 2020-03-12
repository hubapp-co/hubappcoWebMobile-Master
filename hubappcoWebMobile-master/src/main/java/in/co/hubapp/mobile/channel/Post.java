
package in.co.hubapp.mobile.channel;

import java.util.Arrays;



public class Post {

	private Long id;

	private String postTitle;

	private String postDescription;

	private String postImageUrl;

	private String likes;

	private byte[] postImage;

	private Long postUserId;

	public Post() {
		super();
	}

	public Post(String postTitle, String postDescription, String postImageUrl, String likes, Long postUserId) {
		super();
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.postImageUrl = postImageUrl;
		this.likes = likes;
		this.postUserId = postUserId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getPostImageUrl() {
		return postImageUrl;
	}

	public void setPostImageUrl(String postImageUrl) {
		this.postImageUrl = postImageUrl;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public byte[] getPostImage() {
		return postImage;
	}

	public void setPostImage(byte[] postImage) {
		this.postImage = postImage;
	}

	public Long getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(Long postUserId) {
		this.postUserId = postUserId;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + "]";
	}

}

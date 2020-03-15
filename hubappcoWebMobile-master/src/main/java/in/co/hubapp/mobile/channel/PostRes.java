
package in.co.hubapp.mobile.channel;

import java.util.Arrays;

public class PostRes {

	private Long id;

	private String postTitle;

	private String postDescription;

	private String postImageUrl;

	private String likes;

	private byte[] postImage;

	private Long postUserId;

	private String categoryName;

	private String categoryChildName;

	private String categorySubChildName;

//	private CategoryFinalChild categoryFinalChild;

//	private String categoryFinalChildName;

	public PostRes() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryChildName() {
		return categoryChildName;
	}

	public void setCategoryChildName(String categoryChildName) {
		this.categoryChildName = categoryChildName;
	}

	public String getCategorySubChildName() {
		return categorySubChildName;
	}

	public void setCategorySubChildName(String categorySubChildName) {
		this.categorySubChildName = categorySubChildName;
	}

	@Override
	public String toString() {
		return "PostRes [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + ", categoryName=" + categoryName + ", categoryChildName="
				+ categoryChildName + ", categorySubChildName=" + categorySubChildName + "]";
	}

}

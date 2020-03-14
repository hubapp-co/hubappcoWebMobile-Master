
package in.co.hubapp.mobile.channel;

import java.util.Arrays;

public class PostReq {

	private Long id;

	private String postTitle;

	private String postDescription;

	private String postImageUrl;

	private String likes;

	private byte[] postImage;

	private Long postUserId;

	private Integer categoryId;

	private String categoryName;

	private Integer categoryChildId;

	private String categoryChildName;

	private Integer categorySubChildId;

	private String categorySubChildName;

	private Integer categoryFinalChildId;

	private String categoryFinalChildName;

	public PostReq() {
		super();
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(Integer categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	public String getCategoryChildName() {
		return categoryChildName;
	}

	public void setCategoryChildName(String categoryChildName) {
		this.categoryChildName = categoryChildName;
	}

	public Integer getCategorySubChildId() {
		return categorySubChildId;
	}

	public void setCategorySubChildId(Integer categorySubChildId) {
		this.categorySubChildId = categorySubChildId;
	}

	public String getCategorySubChildName() {
		return categorySubChildName;
	}

	public void setCategorySubChildName(String categorySubChildName) {
		this.categorySubChildName = categorySubChildName;
	}

	public Integer getCategoryFinalChildId() {
		return categoryFinalChildId;
	}

	public void setCategoryFinalChildId(Integer categoryFinalChildId) {
		this.categoryFinalChildId = categoryFinalChildId;
	}

	public String getCategoryFinalChildName() {
		return categoryFinalChildName;
	}

	public void setCategoryFinalChildName(String categoryFinalChildName) {
		this.categoryFinalChildName = categoryFinalChildName;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", categoryChildId=" + categoryChildId + ", categoryChildName=" + categoryChildName
				+ ", categorySubChildId=" + categorySubChildId + ", categorySubChildName=" + categorySubChildName
				+ ", categoryFinalChildId=" + categoryFinalChildId + ", categoryFinalChildName="
				+ categoryFinalChildName + "]";
	}

}

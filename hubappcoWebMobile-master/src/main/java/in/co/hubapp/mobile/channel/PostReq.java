
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

	private Long categoryId;

	private Long categoryChildId;

	private Long categorySubChildId;

	private Long categoryFinalChildId;

	private Long docId;

	public PostReq() {
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(Long categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	public Long getCategorySubChildId() {
		return categorySubChildId;
	}

	public void setCategorySubChildId(Long categorySubChildId) {
		this.categorySubChildId = categorySubChildId;
	}

	public Long getCategoryFinalChildId() {
		return categoryFinalChildId;
	}

	public void setCategoryFinalChildId(Long categoryFinalChildId) {
		this.categoryFinalChildId = categoryFinalChildId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	@Override
	public String toString() {
		return "PostReq [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + ", categoryId=" + categoryId + ", categoryChildId=" + categoryChildId
				+ ", categorySubChildId=" + categorySubChildId + ", categoryFinalChildId=" + categoryFinalChildId
				+ ", docId=" + docId + "]";
	}

}

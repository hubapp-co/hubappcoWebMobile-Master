
package in.co.hubapp.mobile.channel;

import java.util.Arrays;

import in.co.hubapp.model.Category;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.CategoryFinalChild;
import in.co.hubapp.model.CategorySubChild;

public class PostRes {

	private Long id;

	private String postTitle;

	private String postDescription;

	private String postImageUrl;

	private String likes;

	private byte[] postImage;

	private Long postUserId;

	private Category category;

//	private String categoryName;

	private CategoryChild categoryChild;

//	private String categoryChildName;

	private CategorySubChild categorySubChild;

//	private String categorySubChildName;

	private CategoryFinalChild categoryFinalChild;

//	private String categoryFinalChildName;

	public PostRes() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategoryChild getCategoryChild() {
		return categoryChild;
	}

	public void setCategoryChild(CategoryChild categoryChild) {
		this.categoryChild = categoryChild;
	}

	public CategorySubChild getCategorySubChild() {
		return categorySubChild;
	}

	public void setCategorySubChild(CategorySubChild categorySubChild) {
		this.categorySubChild = categorySubChild;
	}

	public CategoryFinalChild getCategoryFinalChild() {
		return categoryFinalChild;
	}

	public void setCategoryFinalChild(CategoryFinalChild categoryFinalChild) {
		this.categoryFinalChild = categoryFinalChild;
	}

	@Override
	public String toString() {
		return "PostRes [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + ", category=" + category + ", categoryChild=" + categoryChild
				+ ", categorySubChild=" + categorySubChild + ", categoryFinalChild=" + categoryFinalChild + "]";
	}

}

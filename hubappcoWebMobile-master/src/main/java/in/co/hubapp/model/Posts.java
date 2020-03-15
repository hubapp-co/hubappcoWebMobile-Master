
package in.co.hubapp.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name = "post")
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "post_title")
	private String postTitle;

	@Column(name = "post_description")
	private String postDescription;

	@Column(name = "post_img_url")
	private String postImageUrl;

	@Column(name = "likes")
	private String likes;

	@Column(name = "post_image_id")
	private Long imageId;

	@Transient
	private byte[] postImage;

	@Column(name = "post_user_id")
	private Long postUserId;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_child_id")
	private Long categoryChildId;

	@Column(name = "category_sub_child_id ")
	private Long categorySubChildId;

	@Column(name = "category_final_child_id")
	private Long categoryFinalChildId;

	public Posts() {
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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

	@Override
	public String toString() {
		return "Posts [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", imageId=" + imageId + ", postImage="
				+ Arrays.toString(postImage) + ", postUserId=" + postUserId + ", categoryId=" + categoryId
				+ ", categoryChildId=" + categoryChildId + ", categorySubChildId=" + categorySubChildId
				+ ", categoryFinalChildId=" + categoryFinalChildId + "]";
	}

}

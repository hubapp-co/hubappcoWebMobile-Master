
package in.co.hubapp.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@Transient
	private byte[] postImage;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_user_id", referencedColumnName = "id")
	private User postUserId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category categoryId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_child_id", referencedColumnName = "id")
	private CategoryChild categoryChildId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = " 	category_sub_child_id ", referencedColumnName = "id")
	private CategorySubChild categorySubChildId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_final_child_id", referencedColumnName = "id", nullable = true)
	private CategoryFinalChild categoryFinalChildId;

	public Posts() {
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

	public User getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(User postUserId) {
		this.postUserId = postUserId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryChild getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(CategoryChild categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	public CategorySubChild getCategorySubChildId() {
		return categorySubChildId;
	}

	public void setCategorySubChildId(CategorySubChild categorySubChildId) {
		this.categorySubChildId = categorySubChildId;
	}

	public CategoryFinalChild getCategoryFinalChildId() {
		return categoryFinalChildId;
	}

	public void setCategoryFinalChildId(CategoryFinalChild categoryFinalChildId) {
		this.categoryFinalChildId = categoryFinalChildId;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + ", postImage=" + Arrays.toString(postImage)
				+ ", postUserId=" + postUserId + ", categoryId=" + categoryId + ", categoryChildId=" + categoryChildId
				+ ", categorySubChildId=" + categorySubChildId + ", categoryFinalChildId=" + categoryFinalChildId + "]";
	}

}

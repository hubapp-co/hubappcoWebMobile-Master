
package in.co.hubapp.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

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

	
	public Posts() {
		super();
	}

	public Posts(String postTitle, String postDescription, String postImageUrl, String likes) {
		super();
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.postImageUrl = postImageUrl;
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postImageUrl=" + postImageUrl + ", likes=" + likes + "]";
	}

		
	

}

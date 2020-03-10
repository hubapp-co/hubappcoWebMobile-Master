package in.co.hubapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_sub_child")
public class CategorySubChild {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@Column(name = "category_sub_child_name")
	private String categorySubChildName;

	@Column(name = "category_child_id")
	private String categoryChildId;

	public CategorySubChild() {
	}

	public CategorySubChild(String categorySubChildName) {
		this.categorySubChildName = categorySubChildName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategorySubChildName() {
		return categorySubChildName;
	}

	public void setCategorySubChildName(String categorySubChildName) {
		this.categorySubChildName = categorySubChildName;
	}

	public String getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(String categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	@Override
	public String toString() {
		return "CategorySubChild [id=" + id + ", categorySubChildName=" + categorySubChildName + ", categoryChildId="
				+ categoryChildId + "]";
	}

}
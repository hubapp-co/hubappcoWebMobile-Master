package in.co.hubapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_child")
public class CategoryChild {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "category_child_name")
	private String categoryChildName;

	@Column(name = "category_id")
	private Long categoryId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_child_id", referencedColumnName = "id")
	private List<CategorySubChild> subCategoriesChild;

	public CategoryChild() {
	}

	public CategoryChild(String categoryChildName, Long categoryId) {
		super();
		this.categoryChildName = categoryChildName;
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryChildName() {
		return categoryChildName;
	}

	public void setCategoryChildName(String categoryChildName) {
		this.categoryChildName = categoryChildName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<CategorySubChild> getSubCategoriesChild() {
		return subCategoriesChild;
	}

	public void setSubCategoriesChild(List<CategorySubChild> subCategoriesChild) {
		this.subCategoriesChild = subCategoriesChild;
	}

	@Override
	public String toString() {
		return "CategoryChild [id=" + id + ", categoryChildName=" + categoryChildName + ", categoryId=" + categoryId
				+ ", subCategoriesChild=" + subCategoriesChild + "]";
	}

}
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
@Table(name = "category_sub_child")
public class CategorySubChild {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@Column(name = "category_sub_child_name")
	private String categorySubChildName;

	@Column(name = "category_child_id")
	private Long categoryChildId;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_sub_child_id", referencedColumnName = "id")
	private List<CategoryFinalChild> finalCategoriesChild;

	public CategorySubChild() {
	}

	

	public CategorySubChild(String categorySubChildName, Long categoryChildId) {
		super();
		this.categorySubChildName = categorySubChildName;
		this.categoryChildId = categoryChildId;
	}



	
	public List<CategoryFinalChild> getFinalCategoriesChild() {
		return finalCategoriesChild;
	}



	public void setFinalCategoriesChild(List<CategoryFinalChild> finalCategoriesChild) {
		this.finalCategoriesChild = finalCategoriesChild;
	}



	public String getCategorySubChildName() {
		return categorySubChildName;
	}

	public void setCategorySubChildName(String categorySubChildName) {
		this.categorySubChildName = categorySubChildName;
	}

	public Long getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(Long categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	@Override
	public String toString() {
		return "CategorySubChild [id=" + id + ", categorySubChildName=" + categorySubChildName + ", categoryChildId="
				+ categoryChildId + "]";
	}

}
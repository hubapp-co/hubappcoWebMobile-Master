package in.co.hubapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_final_child")
public class CategoryFinalChild {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@Column(name = "final_child_name")
	private String finalChildName;

	@Column(name = "category_sub_child_id")
	private Long categorySubChildId;

	public CategoryFinalChild() {
		super();
	}

	

	public CategoryFinalChild(String finalChildName, Long categorySubChildId) {
		super();
		this.finalChildName = finalChildName;
		this.categorySubChildId = categorySubChildId;
	}



	public String getFinalChildName() {
		return finalChildName;
	}

	public void setFinalChildName(String finalChildName) {
		this.finalChildName = finalChildName;
	}

	public Long getCategorySubChildId() {
		return categorySubChildId;
	}

	public void setCategorySubChildId(Long categorySubChildId) {
		this.categorySubChildId = categorySubChildId;
	}

	@Override
	public String toString() {
		return "CategoryFinalChild [id=" + id + ", finalChildName=" + finalChildName + ", categorySubChildId="
				+ categorySubChildId + "]";
	}

	

}
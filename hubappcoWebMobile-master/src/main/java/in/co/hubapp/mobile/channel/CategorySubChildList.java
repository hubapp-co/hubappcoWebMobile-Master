package in.co.hubapp.mobile.channel;

public class CategorySubChildList {

	private Long id;

	String categorySubChildName;

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

	public CategorySubChildList() {
		super();
	}

	@Override
	public String toString() {
		return "CategorySubChildList [id=" + id + ", categorySubChildName=" + categorySubChildName + "]";
	}

}

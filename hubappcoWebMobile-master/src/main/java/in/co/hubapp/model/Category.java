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
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="category_name")
    private String categoryName;

    @OneToMany( fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<CategoryChild> subCategories;
    
    public Category() {}

    

    public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	public List<CategoryChild> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<CategoryChild> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", subCategories=" + subCategories + "]";
	}

	   
}
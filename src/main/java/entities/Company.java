package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	// Relation OneToMany
	// Une entreprise peut posséder plusieurs filiales
	// mappée par l'objet company de l'entité Branch (filiale)
	@OneToMany(mappedBy ="company", cascade = CascadeType.ALL)
	// L'entreprise a une liste de filiales 
	private Set <Branch> branches;
	
	
	
	// Méthode d'ajout de filiales à l'entreprise
	public void addBranch (Branch branch) {
		this.branches.add(branch);
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Branch> getBranches() {
		return branches;
	}
	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", branches=" + branches + "]";
	}
	
	
	
}

 package entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="sector")
public class Sector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String location;
	
	@OneToMany(mappedBy="sector", cascade = CascadeType.ALL)
	private Set <Employee> employees;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sector_has_branch", 
				joinColumns = @JoinColumn (name="id_sector"),
				inverseJoinColumns = @JoinColumn (name ="id_branch"))
	private Set<Branch> branches = new HashSet<Branch>();
	
	
	
	// Methode permettant l'ajout d'employés dans le secteur
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	// Methode permettant l'ajout de filiales au secteur
	public void addBranch(Branch branch) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	
	
	

}

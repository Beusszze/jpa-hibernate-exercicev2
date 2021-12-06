package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int employees;
	
	// Relation Many to one 
	// Plusieurs filiales peuvent appartenir à une seule entreprise
	// Une filiale n'est rattachée qu'à une entreprise mère
	@ManyToOne
	// Nom de la colonne dans la bdd où la jointure se fait 
	@JoinColumn(name="id_company")
	private Company company;
	
	// Relation Many to many
	// Une filiale peut contenir plusieurs secteurs
	// Un secteur peut appartenir à plusieurs filiales
	// Une filiale contient donc une liste de secteurs
	@ManyToMany(mappedBy="branches")
	private Set<Sector> sectors = new HashSet<Sector>();
	
	
	// Méthode d'ajout de secteur à la filiale
	public void addSector (Sector sector) {
		this.sectors.add(sector);
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

	public int getEmployees() {
		return employees;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(Set<Sector> sectors) {
		this.sectors = sectors;
	}


	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", employees=" + employees + ", company=" + company
				+ ", sectors=" + sectors + "]";
	}
	
	

	
	
}

package manager;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Branch;
import entities.Company;
import entities.Sector;

public class SectorManager {

	public static void main(String[] args) {
		SessionFactory sessionFactory;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			// Instanciation de l'entreprise, settage d'un nom
			Company company = new Company ();
			company.setName("Mon Entreprise");
			
			// Instanciation de deux filiales avec un nombre d'employé, un nom et une affiliation à l'entreprise mère
			Branch branch1 = new Branch();
			branch1.setEmployees(8);
			branch1.setName("Super filiale");
			branch1.setCompany(company);
						
			
			Branch branch2 = new Branch();
			branch2.setEmployees(4);
			branch2.setName("PME lambda");
			branch2.setCompany(company);

			// Liste de filiales créée, ajout des deux filiales et affiliation de cette liste à l'entreprise mère
			// Les filiales ont donc une entreprise mère
			// L'entreprise a une liste de filiales
			Set<Branch> branches = new HashSet<Branch>();
			branches.add(branch1);
			branches.add(branch2);
			company.setBranches(branches);
			
			
			Sector sector1 = new Sector ();
			sector1.setLocation("Sud");
			sector1.setName("Safe zone");
			sector1.addBranch(branch1);
			sector1.addBranch(branch2);
			sector1.setBranches(branches);
			
			
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
	}

}

package manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Employee;

public class EmployeeManager {
	
	protected SessionFactory sessionFactory;
	protected List <Employee> employees;
	
	
	// CRUD
	/**
	 * Méthode qui initialise la connexion avec la base de donnée
	 */
	protected void setup() {
		// charge une session Hibernate, la récupère pour créer un registry
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		try {
			// Essaie de se connecter à la bdd
			// Construction de la session
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		} catch (Exception e) {
			// catch une exception si la connexion ne s'établit pas
			StandardServiceRegistryBuilder.destroy(registry);
			e.getStackTrace();
		}
		
	}
	
	/**
	 * Méthode qui ferme la session Hibernate
	 */
	protected void exit () {
		sessionFactory.close();
	}
		
	/**
	 * Méthode permettant la création d'enregistrement dans la base de donnée
	 */
	protected void create () {
		List <Employee> employees  = new ArrayList();
		Employee employee1 = new Employee ();
		employee1.setLastName("Nakamoto");
		employee1.setFirstName("Satoshi");
		employee1.setMailAdress("sato@gmail.com");
		employee1.setRole("CEO");
		employee1.setPhoneNumber("0606060606");
		employee1.setAdress("1 rue des Huns");
		employees.add(employee1);

		
		
		
		Employee employee2 = new Employee ();
		employee2.setLastName("Aouar");
		employee2.setFirstName("Houssem");
		employee2.setMailAdress("aouar@ol.fr");
		employee2.setRole("Developpeur");
		employee2.setPhoneNumber("0707070707");
		employee2.setAdress("2 rue des Deux");
		employees.add(employee2);


		Employee employee3 = new Employee ();
		employee3.setLastName("Nom de famille");
		employee3.setFirstName("Mon prénom");
		employee3.setMailAdress("pasdadresse@gmail.com");
		employee3.setRole("Ingénieur");
		employee3.setPhoneNumber("0706070607");
		employee3.setAdress("3 rue de Troie");
		employees.add(employee3);

		


		
		Session session1 = sessionFactory.openSession(); //Ouverture d'une session de connexion à la bdd
		session1.beginTransaction(); //transaction entre le back et la bdd
		session1.save(employee1); // ORM récupère la requête de la bdd 
		session1.getTransaction().commit(); // Sauvegarde de cette requête 
		session1.close(); //fermeture de la session, les données ont été récupéré
		
		
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		session2.save(employee2);
		session2.getTransaction().commit();
		session2.close();
		
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();
		session3.save(employee3);
		session3.getTransaction().commit();
		session3.close();


		
	}
	/**
	 * Méthode qui permet la lecture d'un enregistrement à partir de son id
	 * @param id l'id de l'employé que l'on cherche
	 * @return l'employé correspondant à l'id
	 */
	protected Employee findById (long id) {	
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, id);
		employee.toString();
		return employee;
	}

	protected void findAll() {
		Session session = sessionFactory.openSession();
	//	List<Employee> employees = new ArrayList<Employee>();	
	//	employees = session.createQuery("SELECT e FROM employee e", Employee.class).getResultList();
		
	//	employees.toString();
	 System.out.println(session.createQuery("SELECT e FROM employee e", Employee.class).getResultList());;
	}		
	
	
	/**
	 * Méthode qui permet la mise à jour d'un enregistrement
	 * @param id l'id de l'employé à mettre à jour 
	 * @param newEmployee le nouvel employé 
	 */
	protected void update (long id, Employee newEmployee) {	
		// Récupère l'employé grâce à la méthode findById
		Employee employee = this.findById(id);
		
		// Série de conditions 
		// Si un attribut de l'employé est différent de null alors remplacer la valeur de cet attribut
		// par celle du nouvel employé 
		if (newEmployee.getLastName() != null) {
			employee.setLastName(newEmployee.getLastName());
		}
		if (newEmployee.getFirstName() != null) {
			employee.setFirstName(newEmployee.getFirstName());
		}
		if (newEmployee.getMailAdress() != null) {
			employee.setMailAdress(newEmployee.getMailAdress());
		}
		if (newEmployee.getRole() != null) {
			employee.setRole(newEmployee.getRole());
		}
		if (newEmployee.getPhoneNumber() != employee.getPhoneNumber()) {
			employee.setPhoneNumber(newEmployee.getPhoneNumber());
		}
		if (newEmployee.getAdress() != null) {
			employee.setAdress(newEmployee.getAdress());
		}
		
		// Utilisation d'une session pour effectuer la mise à jour de la base de donnée
		// fonction .update qui enregistre les modifications apportées à l'employé 
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
		session.close();
		
	}
	/**
	 * Méthode permettant la suppression d'un employé dans la bdd à partir de son id
	 * @param employee l'employé à supprimer
	 */
	protected void delete (long id) {	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(this.findById(id));
		session.getTransaction().commit();
		session.close();
	}
	
	
	public static void main (String[] args) {
		EmployeeManager manager = new EmployeeManager();
		manager.setup();
		manager.exit();
	}
	

}

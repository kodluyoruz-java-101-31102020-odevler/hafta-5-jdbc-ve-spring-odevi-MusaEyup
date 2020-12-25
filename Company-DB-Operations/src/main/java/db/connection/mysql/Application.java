package db.connection.mysql;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import db.connection.mongo.connection.collection.MongoEmployeeCollectionDao;
import db.connection.mysql.connection.dao.DepartmentDAO;
import db.connection.mysql.connection.dao.EmployeeDAO;
import db.connection.mysql.connection.dao.ManagerDAO;
import db.connection.mysql.connection.dao.SalaryDAO;
import db.connection.mysql.connection.model.Department;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.EmployeeProfile;
import db.connection.mysql.connection.model.Manager;
import db.connection.mysql.connection.service.DepartmentService;
import db.connection.mysql.connection.service.EmployeeService;
import db.connection.mysql.connection.service.ManagerService;
import db.connection.mysql.connection.service.SalaryService;

public class Application {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		SalaryDAO salaryDAO = new SalaryDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		MongoEmployeeCollectionDao mongoEmployeeCollectionDao = new MongoEmployeeCollectionDao("employee");
		
		
		
		EmployeeService employeeService = new EmployeeService(employeeDAO, mongoEmployeeCollectionDao);
		SalaryService salaryService = new SalaryService(salaryDAO);
		ManagerService managerService = new ManagerService(managerDAO);
		DepartmentService departmentService = new DepartmentService(departmentDAO);
		
		
		runPresentationLayer(employeeService, salaryService, managerService, departmentService);
	}
	
	
	private static void runPresentationLayer(
			EmployeeService employeeService, 
			SalaryService salaryService,
			ManagerService managerService,
			DepartmentService departmentService) {
		
		showMenu();
		
		while(true) {
			
			int choice = makeProcessChoice();
			
			if(choice == 10) {
				break;
			}
			
			switch (choice) {
				case 1:
					showAllEmployees(employeeService);
					System.out.println();
					break;
				case 2:
					showEmployeeProfile(employeeService, salaryService);
					System.out.println();
					break;
				case 3:
					insertEmployee(employeeService);
					System.out.println();
					break;
				case 4:
					updateEmployee(employeeService);
					System.out.println();
					break;
				case 5:
					deleteEmployee(employeeService);
					System.out.println();
					break;
				case 6:
					showMenu();
					System.out.println();
					break;
				case 7:
					// burada aktif yöneticileri listeleyen bir fonksiyon yazmalýsýnýz.
					listActiveManagers(managerService);
					break;
				case 8:
					// burada tüm departmanlarý listeleyiniz.
					listDepartments(departmentService);
					break;
				case 9:
					draftEmployeeProfileOperations(employeeService, salaryService);
					break;
			}
			
		}
	}
	
	
	private static void showMenu() {
		
		System.out.println("1- Tüm Çalýþan listesi ");
		System.out.println("2- Çalýþan profili sorgulama ");
		System.out.println("3- Yeni Çalýþan ekleme ");
		System.out.println("4- Çalýþan verilerini güncelleme ");
		System.out.println("5- Çalýþan silme ");
		System.out.println("6- Menü tekrar yazdýr ");
		System.out.println("7- Aktif yöneticilik yapanlarý listele ");
		System.out.println("8- Departmanlarý listele ");
		System.out.println("9- Geçici profil iþlemleri ");
		System.out.println("10- Çýkýþ ");
	}
	
	public static int makeProcessChoice() {
		
		System.out.println("Ýþlem tercihinizi yapýnýz...");
		int choice = scanner.nextInt();
		return choice;
	}
	
	public static void showAllEmployees(EmployeeService employeeService) {
		
		Set<Employee> employees = employeeService.findAll();
		
		Iterator<Employee> iterator = employees.iterator();
		while(iterator.hasNext()) {
			
			Employee employee = iterator.next();
			System.out.println(employee);
		}
	}
	
	public static void showEmployeeProfile(EmployeeService employeeService, SalaryService salaryService) {
		
		System.out.println("çalýþan ID'sini giriniz: ");
		long empNo = scanner.nextLong();
		
		EmployeeProfile employeeProfile = employeeService.loadEmployeeProfile(empNo);
		
		if(employeeProfile == null) {
			System.out.println("Girdiðiniz ID deðerine uygun bir çalýþan profili bulunamamýþtýr");
			return;
		}
		
		List<Long> salaries = salaryService.getSalaries(empNo);
		employeeProfile.setSalaries(salaries);
		
		System.out.println(employeeProfile.getEmployee());
		System.out.println("çalýþtýðý Departman: " + employeeProfile.getDepartmentName());
		System.out.println("Maaþlarý:");
		for(Long salary : employeeProfile.getSalaries()) {
			System.out.println(salary);
		}
	}
	
	public static void insertEmployee(EmployeeService employeeService) {
		
		System.out.println("çalýþan verilerini giriniz:");
		
		scanner.nextLine();
		
		System.out.println("Ýsim");
		String name = scanner.nextLine();
		
		System.out.println("Soyismi");
		String lastName = scanner.nextLine();
		
		System.out.println("Cinsiyet (M veya F giriniz)");
		String gender = scanner.next();
		gender = String.valueOf(gender.charAt(0));
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setLastName(lastName);
		employee.setGender(gender);
		employee.setHireDate(new Date());
		employee.setBirthDate(new Date());
		
		employee = employeeService.save(employee);
		System.out.println("Yeni oluþturulan çalýþan bilgileri");
		System.out.println(employee);
	}
	
	public static void updateEmployee(EmployeeService employeeService) {
		
		System.out.println("çalýþan verilerini giriniz:");
		
		System.out.println("çalýþan ID'sini giriniz: ");
		long empNo = scanner.nextLong();
		
		scanner.nextLine();
		
		System.out.println("Ýsim");
		String name = scanner.nextLine();
		
		System.out.println("Soyismi");
		String lastName = scanner.nextLine();
		
		Employee employee = employeeService.update(empNo, name, lastName);
		
		System.out.println("Bilgileri güncellenen çalýþan kaydý");
		System.out.println(employee);
	}
	
	public static void deleteEmployee(EmployeeService employeeService) {
		
		System.out.println("Çalýþan ID'sini giriniz: ");
		long empNo = scanner.nextLong();
		
		boolean result = employeeService.delete(empNo);
		 
		if(!result) 
		{
			System.out.println("Girdiðiniz ID deðerine sahip bir çalýþan bulunmamaktadýr");
			return;
		}
		else
		{
			System.out.println(empNo + " ID'li çalýþan silinmiþtir!");
		}
	}
	
	public static void listActiveManagers(ManagerService managerService) {
		
		List<Manager> managersList = managerService.listAllManager();
		System.out.println(managersList.size() + " Aktif yönetici");
		managersList.stream().forEach(System.out::println);
		
	}
	
	public static void listDepartments(DepartmentService departmentService) {
		
		// Burada tüm departmanlarý listeleyen ve ekrana gösteren kodu yazýnýz.
		List<Department> departmentList = departmentService.findAll();
		
		System.out.println(departmentList.size() + " Departman mevcut");
		departmentList.stream().forEach(System.out::println);
		
	}
	
	public static void draftEmployeeProfileOperations(EmployeeService employeeService, SalaryService salaryService) {
		
		System.out.println("1- Yeni profil bilgisi kaydetmek");
		System.out.println("2- Profil bilgisi almak");

		int choice = scanner.nextInt();
		
		if(choice == 1) {
			
			System.out.println("çalýþan ID'sini giriniz: ");
			long empNo = scanner.nextLong();
			
			EmployeeProfile employeeProfile = employeeService.loadEmployeeProfile(empNo);
			
			if(employeeProfile == null) {
				System.out.println("Girdiðiniz ID deðerine uygun bir çalýþan profili bulunamamýþtýr");
				return;
			}
			
			List<Long> salaries = salaryService.getSalaries(empNo);
			employeeProfile.setSalaries(salaries);
			
			
			boolean saveResult = employeeService.saveAsDraft(employeeProfile);
			if(saveResult) {
				System.out.println("Draft baþarýyla kaydedildi.");
			}
			else {
				System.out.println("Draft kaydedilemedi!");
			}
			
		}
		else if(choice == 2) {
			
			System.out.println("çalýþan ID'sini giriniz: ");
			long empNo = scanner.nextLong();
			
			EmployeeProfile employeeProfile = employeeService.loadProfileAsDraft(empNo);
			
			if(employeeProfile == null) {
				System.out.println("Girdiðiniz ID deðerine uygun bir çalýþan profili bulunamamýþtýr");
				return;
			}
			
			System.out.println(employeeProfile);
		}
		
	}

}

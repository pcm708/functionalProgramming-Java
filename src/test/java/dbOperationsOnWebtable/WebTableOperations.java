package dbOperationsOnWebtable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

import object.Employee;

public class WebTableOperations extends Parent{
	
	@Test
	(description = "Get the data from the webtable")
	public void getWebTableData() {
		empList.forEach(emp->System.out.println(emp.getName()+emp.getPosition()+emp.getOffice()+emp.getSalary()));
	}
	
	@Test
	(description = "Get the name of the employee whose age is less than 22")
	public void getNameOfEmployeeHavingAgeLessThan22() {
		empList.stream()
				.filter(emp->emp.getAge()<22)
				.forEach(emp->System.out.println(emp.getName()));
	}
	
	@Test
	(description = "Get the 3rd highest salary")
	public void getThirdHighestSalary() {
		List<Double> salaryList = empList.stream()
				.map(emp->emp.getSalary())
				.sorted().collect(Collectors.toList());
		Collections.reverse(salaryList);
		System.out.println("### Third highest salary: "+salaryList.get(2));
		
////		Optional: To get the employee data
//		System.out.println("\nEmployee Details:");
//		empList.stream()
//				.filter(emp->emp.getSalary().equals(salaryList.get(2)))
//				.forEach(emp->System.out.println(emp.getName()+"... "+emp.getSalary()));
	}
	
	@Test
	(description = "Get duplicate record of the table")
	public void getDuplicateRecords() {
		//This web table doesn't has any duplicate  record
		//So Adding a duplicate data explicitly in my empList
		Employee newEmp = new Employee("Airi Satou","Accountant","Tokyo",33,162700.0); 
		empList.add(newEmp);
		Set<String> set = new HashSet<>();
		
		empList.stream()
				.filter(emp->!set.add(emp.getName()))
				.forEach(emp->System.out.println(emp.getName()));
		
		//Performing Undo Operation
		empList.remove(newEmp);
	}
	
	@Test
	(description = "Get employee names having salary>$50,000")
	public void getEmployeeNameHavingSalaryGreaterThan50000() {
		//Below is a weird printing syntax just to adjust the data in a tabular format
		System.out.printf("%-35s%-6s\n","Employee Name","Salary");
		empList.stream()
				.filter(emp->emp.getSalary()>50000.0)
				.forEach(emp->
				System.out.printf("%-35s%-6.0f\n",emp.getName(),emp.getSalary()));
	}
	
	@Test
	(description = "Get total number of software engineers")
	public void getCountOfSoftwareEngineers() {
		System.out.println("Total Software Engineers: "+
									empList.stream()
									.filter(emp->emp.getPosition().equals("Software Engineer"))
									.count());
	}
	
	@Test
	(description = "Get the name of employee from Tokyo branch")
	public void getNameOFEmployeesFromTokYoBranch() {
		empList.stream()
				.filter(emp->emp.getOffice().equals("Tokyo"))
				.forEach(emp->System.out.println(emp.getName()+emp.getOffice()));
	}
	
	@Test
	(description = "Get unique Offices Locations")
	public void getUniqueOfficeLocations() {
		empList.stream()
				.map(emp->emp.getOffice())
				.distinct()
				.forEach(office->System.out.println(office));
	}
	
	@Test
	(description = "Get the name of employee having Maximum Salary")
	public void getNameOfTheEmployeeHavingMaximumSalary() {
		Double salary = empList.stream()
				.map(emp->emp.getSalary())
				.max(Double::compare).get();
		
		empList.stream()
				.filter(emp->emp.getSalary().equals(salary))
				.forEach(emp->System.out.println(emp.getPosition()+" "+emp.getName()));
	}
	
	@Test
	(description = "Get the maximum salary from each department")
	public void getMaximumSalaryFromEachDepartment() { //Position~~Department
		Set<String> departments = empList.stream()
				.map(emp->emp.getPosition())
				.distinct()
				.collect(Collectors.toSet());
		
		//Considering all the positions as different Departments of an organization
		System.out.println("### Highest salary under different departments\n");
		for(String dept: departments) {
			 System.out.printf("%-35s%-6.0f\n",dept,
					empList.stream()
					.filter(emp->emp.getPosition().equals(dept))
					.map(emp->emp.getSalary())
					.max(Double::compare).get());
		}
	}
	
	@Test
	(description = "Get the youngest of all employees")
	public void getTheYoungestEmployeeDetails() {
		System.out.println("### Youngest Employee Details\n");
		int youngestAge = empList.stream().map(emp->emp.getAge()).min(Integer::compare).get();
		
		empList.stream()
				.filter(emp->emp.getAge()==youngestAge)
				.forEach(emp->System.out.println(emp.getName()+"... "+emp.getSalary()));
	}
	
	
	@Test
	(description = "Get the oldest of all employees under different departments")
	public void getAgeOfSeniorEmployeeFromEachDepartment() {
		Set<String> departments = empList.stream()
				.map(emp->emp.getPosition())
				.distinct().collect(Collectors.toSet());
		System.out.println("### Age of senior most employee in different departments\n");
		for(String dept: departments) {
			System.out.printf("%-35s%-6d\n",dept,
			empList.stream()
					.filter(emp->emp.getPosition().equals(dept))
					.map(emp->emp.getAge())
					.sorted()
					.max(Integer::compare).get());
		}
	}
}
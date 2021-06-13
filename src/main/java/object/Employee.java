package object;

public class Employee {
	private String name;
	private String position;
	private String office;
	private int age;
	private Double salary;
	
	public Employee(String name, String position, String office, int age, Double salary) {
		this.name = name;
		this.position = position;
		this.office = office;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public String getOffice() {
		return office;
	}

	public int getAge() {
		return age;
	}

	public Double getSalary() {
		return salary;
	}
}
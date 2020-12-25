package db.connection.mysql.connection.model;

public class Manager {

	private Employee employee;
	private String departmentName;
	
	public Manager(Employee employee, String departmentName) {
		this.employee = employee;
		this.departmentName = departmentName;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Manager [");
		builder.append(" ID:");
		builder.append(employee.getId());
		builder.append(" , Name:");
		builder.append(employee.getName());
		builder.append(" , Last Name:");
		builder.append(employee.getLastName());
		builder.append(" , Gender:");
		builder.append(employee.getGender());
		builder.append(" , Birth Date:");
		builder.append(employee.getBirthDate());
		builder.append(" , Hire Date:");
		builder.append(employee.getHireDate());
		builder.append(" , Department:");
		builder.append(departmentName);
		builder.append(" ]");
		
		return builder.toString();
	}
	
}

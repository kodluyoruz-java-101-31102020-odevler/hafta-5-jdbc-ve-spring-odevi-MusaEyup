package db.connection.mysql.connection.model;

import java.util.List;

public class EmployeeProfile {

	private Employee employee;
	private String departmentName;
	private List<Long> salaries;
	
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
	public List<Long> getSalaries() {
		return salaries;
	}
	public void setSalaries(List<Long> salaries) {
		this.salaries = salaries;
	}
	@Override
	public String toString() {
		return "EmployeeProfile [employee=" + employee + ", departmentName=" + departmentName + ", salaries=" + salaries
				+ "]";
	}
}

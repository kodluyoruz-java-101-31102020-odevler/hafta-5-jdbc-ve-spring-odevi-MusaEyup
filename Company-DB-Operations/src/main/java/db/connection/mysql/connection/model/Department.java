package db.connection.mysql.connection.model;

public class Department {

	private String name;
	private String deptNo;
	
	public Department(String deptNo, String name) {
		this.name = name;
		this.deptNo = deptNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", deptNo=" + deptNo + "]";
	}
	
}

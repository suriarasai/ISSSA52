package sg.edu.iss.cats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Department class
 *
 * @version $Revision: 1.0
 * @author Suria
 * 
 */

@Entity
@Table(name="department")
public class Department {
	@Id
	@Column(name = "departmentid")
	private String departmentId;
	@Basic
	@Column(name = "managerid")
	private String managerInCharge;

	public Department() {
	}

	public Department(String departmentId) {
		this.setDepartmentId(departmentId);
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setManagerInCharge(String managerInCharge) {
		this.managerInCharge = managerInCharge;
	}

	public String getManagerInCharge() {
		return managerInCharge;
	}

}

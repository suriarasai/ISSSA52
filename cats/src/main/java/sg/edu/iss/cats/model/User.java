package sg.edu.iss.cats.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 * User class
 *
 * @version $Revision: 1.0
 * @author Suria
 * 
 */

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 6529685098267757680L;
	@Id
	@Column(name = "userid")
	private String userId;
	@NotEmpty
	@Column(name = "name")
	private String name;
	@NotEmpty
	@Column(name = "password")
	private String password;
	@Column(name = "employeeid")
	private String employeeId;

	@ManyToMany(targetEntity = Role.class, cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinTable(name = "userrole", joinColumns = {
			@JoinColumn(name = "userid", referencedColumnName = "userid") }, inverseJoinColumns = {
					@JoinColumn(name = "roleid", referencedColumnName = "roleid") }

	)
	private List<Role> roleSet;
	
	@Transient
	private ArrayList<String> roleIds = new ArrayList<String>();

	public User() {
	}

	public User(String userId, String name, String password, String employeeId) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.employeeId = employeeId;
	}

	public User(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(ArrayList<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public ArrayList<String> getRoleIds() {
		ArrayList<Role> rList = (ArrayList<Role>) this.getRoleSet();
		ArrayList<String> roleIds = new ArrayList<String>();
		for (Role role : rList) {
			roleIds.add(role.getRoleId());
		}
		return roleIds;
	}

	public void setRoleIds(ArrayList<String> roleIds) {

		this.roleIds = roleIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", employeeId=" + employeeId
				+ ", roleSet=" + roleSet + ", roleIds=" + roleIds + "]";
	}

}
package sg.edu.iss.cats.services;

import java.util.ArrayList;

import sg.edu.iss.cats.model.Department;

public interface DepartmentService {

	ArrayList<Department> findAllDepartments();

	Department findDepartment(String did);

	Department createDepartment(Department dep);

	Department changeDepartment(Department dep);

	void removeDepartment(Department dep);

}
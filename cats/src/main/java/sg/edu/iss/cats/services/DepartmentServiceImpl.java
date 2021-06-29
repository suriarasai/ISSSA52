package sg.edu.iss.cats.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.cats.model.Department;
import sg.edu.iss.cats.repositories.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private DepartmentRepository departmentRepository;
	
	/* (non-Javadoc)
	 * @see edu.iss.cats.service.DepartmentService#findAllDepartments()
	 */
	@Override
	@Transactional
	public ArrayList<Department> findAllDepartments() {
		ArrayList<Department> l = (ArrayList<Department>) departmentRepository.findAll();
		return l;
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.DepartmentService#findDepartment(java.lang.String)
	 */
	@Override
	@Transactional
	public Department findDepartment(String did) {
		return departmentRepository.findById(did).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.DepartmentService#createDepartment(edu.iss.cats.model.Department)
	 */
	@Override
	@Transactional
	public Department createDepartment(Department dep) {
		return departmentRepository.saveAndFlush(dep);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.DepartmentService#changeDepartment(edu.iss.cats.model.Department)
	 */
	@Override
	@Transactional
	public Department changeDepartment(Department dep) {
		return departmentRepository.saveAndFlush(dep);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.DepartmentService#removeDepartment(edu.iss.cats.model.Department)
	 */
	@Override
	@Transactional
	public void removeDepartment(Department dep) {
		departmentRepository.delete(dep);
	}

}

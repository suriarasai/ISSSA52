package sg.edu.iss.cats.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.cats.model.Employee;
import sg.edu.iss.cats.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#findEmployeesByManager(java.lang.String)
	 */
	@Override
	@Transactional
	public ArrayList<Employee> findEmployeesByManager(String s) {
		return employeeRepository.findEmployeesByManagerId(s);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#findEmployeeById(java.lang.String)
	 */
	@Override
	@Transactional
	public Employee findEmployeeById(String s) {
		return employeeRepository.findEmployeeById(s);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#findAllEmployees()
	 */
	@Override
	@Transactional
	public ArrayList<Employee> findAllEmployees() {
		ArrayList<Employee> l = (ArrayList<Employee>) employeeRepository.findAll();
		return l;
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#findEmployee(java.lang.String)
	 */
	@Override
	@Transactional
	public Employee findEmployee(String empid) {
		return employeeRepository.findById(empid).orElse(null);

	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#createEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Employee createEmployee(Employee emp) {
		return employeeRepository.saveAndFlush(emp);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#changeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public Employee changeEmployee(Employee emp) {
		return employeeRepository.saveAndFlush(emp);
	}

	/* (non-Javadoc)
	 * @see edu.iss.cats.service.EmployeeService#removeEmployee(edu.iss.cats.model.Employee)
	 */
	@Override
	@Transactional
	public void removeEmployee(Employee emp) {
		employeeRepository.delete(emp);
	}
	
	@Transactional
	public ArrayList<String> findAllManagerNames() {
		return employeeRepository.findAllManagerNames();
	}
	
	@Transactional
	public ArrayList<Employee> findAllManagers() {
		return employeeRepository.findAllManagers();
	}

	@Override
	public ArrayList<Employee> findSubordinates(String employeeId) {
		return employeeRepository.findSubordinates(employeeId);
	}

	@Override
	public ArrayList<String> findAllEmployeeIDs() {
		return employeeRepository.findAllEmployeeIDs();
	}


}

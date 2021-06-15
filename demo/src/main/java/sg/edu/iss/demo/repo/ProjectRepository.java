package sg.edu.iss.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.demo.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
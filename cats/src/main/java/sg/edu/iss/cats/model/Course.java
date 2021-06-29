package sg.edu.iss.cats.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import sg.edu.iss.cats.helper.CourseEventEnum;

/**
 * Course class
 *
 * @version $Revision: 1.0
 * @author Suria
 * 
 */

@Entity
@Table(name = "course")

public class Course {
	
	/** Attributes for Course **/
	@Id
	@Column(name = "courseid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	@Basic
	@Column(name = "employeeid")
	private String employeeId;
	@Column(name = "coursename")
	private String courseName;
	@Column(name = "organiser")
	private String organiser;
	@Temporal(TemporalType.DATE)
	@Column(name = "fromdate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "todate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;
	@Basic
	@Column(name = "fees")
	private double fees;
	@Basic
	@Column(name = "gstincluded", columnDefinition = "BIT", length = 1)
	private boolean gstIncluded;
	@Basic
	@Column(name = "justification")
	private String justification;
    @Column(name = "status", columnDefinition = "ENUM('SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
	private CourseEventEnum status;

	/** Container for CourseApplicationActions **/
	@OneToMany(mappedBy="course", cascade={CascadeType.ALL, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	private List<CourseEvent> courseEvent = new ArrayList<CourseEvent>();

	public Course() {
	}

	public Course(int courseId) {
		this.courseId = courseId;
	}

	public Course(int courseId, String employeeId, String courseName, String organiser, Date fromDate, Date toDate,
			double fees, boolean gstIncluded, String justification, CourseEventEnum status, ArrayList<CourseEvent> events) {
		super();
		this.courseId = courseId;
		this.employeeId = employeeId;
		this.courseName = courseName;
		this.organiser = organiser;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fees = fees;
		this.gstIncluded = gstIncluded;
		this.justification = justification;
		this.status = status;
		this.courseEvent.addAll(courseEvent);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOrganiser() {
		return organiser;
	}

	public void setOrganiser(String organiser) {
		this.organiser = organiser;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public boolean isGstIncluded() {
		return gstIncluded;
	}

	public void setGstIncluded(boolean gstIncluded) {
		this.gstIncluded = gstIncluded;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public CourseEventEnum getStatus() {
		return status;
	}

	public void setStatus(CourseEventEnum status) {
		this.status = status;
	}

	public ArrayList<CourseEvent> getEvents() {
		 return (new ArrayList<CourseEvent>(courseEvent));
	}

	public void setEvents(ArrayList<CourseEvent> events) {
		this.courseEvent.addAll(courseEvent);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
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
		Course other = (Course) obj;
		if (courseId != other.courseId)
			return false;
		return true;
	}

}

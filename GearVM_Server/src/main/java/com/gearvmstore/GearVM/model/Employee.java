package com.gearvmstore.GearVM.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	@Column(columnDefinition = "nchar(10)", name = "phone_number")
	private String phoneNumber;
	private double salary;
	@Column(columnDefinition = "nchar(20)", name = "national_id")
	private String nationalId;
	@Column(columnDefinition = "date", name = "data_of_birth")
	private Date dateOfBirth;
	@Column(columnDefinition = "nvarchar(100)")
	private String address;
	@Column(columnDefinition = "nchar(20)")
	private String email;
	@Column(name = "work_status")
	private boolean workStatus;

	@Enumerated(EnumType.ORDINAL)
	private Role role;

	public Employee() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

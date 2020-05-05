package com.example.laboratorio3.repository;

import com.example.laboratorio3.dto.Dto1;
import com.example.laboratorio3.dto.Dto2;
import com.example.laboratorio3.entity.Departments;
import com.example.laboratorio3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

    @Query(value = "SELECT c.country_name, l.city, count(*) FROM \n" +
            "(SELECT d.department_id, d.location_id  FROM hr.departments d INNER JOIN employees e on e.department_id = d.department_id group by d.department_id having count(e.employee_id) >3) d \n" +
            "INNER JOIN locations l on d.location_id = l.location_id \n" +
            "INNER JOIN countries c on c.country_id = l.country_id \n" +
            "GROUP BY l.city",
            nativeQuery = true)
    List<Dto2> reporteDepa();
}

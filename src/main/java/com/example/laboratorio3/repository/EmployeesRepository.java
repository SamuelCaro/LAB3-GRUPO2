package com.example.laboratorio3.repository;


import com.example.laboratorio3.dto.Dto1;
import com.example.laboratorio3.dto.Dto3;
import com.example.laboratorio3.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query(value = "SELECT e.first_name, e.last_name, jh.start_date, jh.end_date, j.job_title  \n" +
            "FROM hr.employees e  \n" +
            "INNER JOIN jobs j  on e.job_id = j.job_id \n" +
            "INNER JOIN job_history jh on e.employee_id = jh.employee_id\n" +
            " where max_salary > 15000",
            nativeQuery = true)
    List<Dto1> buscarEMpleadosSalario();


    @Query(value = "SELECT d.department_name, e.first_name, e.last_name, e.salary FROM departments d \n" +
            "INNER JOIN employees e on e.employee_id = d.manager_id \n" +
            "INNER JOIN (SELECT  employee_id  FROM hr.employees where TIMESTAMPDIFF(YEAR,employees.hire_date ,CURRENT_TIMESTAMP()) > 5) x  \n" +
            "on x.employee_id = e.employee_id",
            nativeQuery = true)
    List<Dto3> gerentesConExp();

}

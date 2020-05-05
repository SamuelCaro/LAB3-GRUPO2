package com.example.laboratorio3.controller;

import com.example.laboratorio3.entity.Employees;
import com.example.laboratorio3.repository.DepartmentsRepository;
import com.example.laboratorio3.repository.EmployeesRepository;
import com.example.laboratorio3.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {


    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    JobsRepository jobsRepository;


    @GetMapping(value = {"", "/listar"})
    public String listaEmployee(Model model) {
        model.addAttribute("listaEmployees", employeesRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(Model model) {
        model.addAttribute("listaEmployees", employeesRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        return "employee/newFrm";
    }

    @PostMapping("/save")
    public String guardarEmployee(Employees employee, RedirectAttributes attr) {
        if (employee.getEmployee_id() == 0) {
            attr.addFlashAttribute("msg", "Employee creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Employee actualizado exitosamente");
        }
        employeesRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String editarEmployee(Model model, @RequestParam("id") int id) {
        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            Employees employee = optEmployees.get();
            model.addAttribute("employee", employee);
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            model.addAttribute("listaJobs", jobsRepository.findAll());
            return "employee/editFrm";
        } else {
            return "redirect:/employee";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Employee borrado exitosamente");
        }
        return "redirect:/employee";

    }

}




package com.bjq.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bjq.springboot.dao.DepartmentDao;
import com.bjq.springboot.dao.EmployeeDao;
import com.bjq.springboot.entities.Department;
import com.bjq.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;


    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    //查询所有员工返回列表页面
    public String list(Model model)
    {
        Collection<Employee> employees = employeeDao.getAll();
        //thymeleaf默认就会拼串
        model.addAttribute("emp",employees);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String aoAddPage(Model model)
    {
        //查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    public String addEmp(Employee employee)
    {
        System.out.println(employee);
        //保存员工
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面,查出当前员工,在页面回显
    @GetMapping("/empedit/{id}")
    public String toEditPage(@PathVariable("id") int id,Model model)
    {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);


        //查询部门
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("depts",departments);

        return "emp/add";
    }


    //完成员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee)
    {
        System.out.println("修改的员工数据"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    //员工的删除操作
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id)
    {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}

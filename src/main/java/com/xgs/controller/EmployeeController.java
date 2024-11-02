package com.xgs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xgs.common.R;
import com.xgs.entity.Employee;
import com.xgs.service.impl.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/employee")
@Slf4j
@RestController
public class EmployeeController {
    private EmployeeService employeeService;


    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getName, employee.getName());
        Employee employeeServiceOne = employeeService.getOne(queryWrapper);
        if (employeeServiceOne == null) {
            return R.error("登录失败");
        }
        if (!employeeServiceOne.getPassword().equals(employee.getPassword())) {
            return  R.error("密码错误，请重新输入");
        }
        if (employeeServiceOne.getStatus()==0) {
            return R.error("该账号已封禁，");
        }
        request.getSession().setAttribute("employee", employeeServiceOne.getId());
        return R.success(employeeServiceOne);
    }
}

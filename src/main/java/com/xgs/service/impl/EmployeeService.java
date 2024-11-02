package com.xgs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xgs.entity.Employee;
import com.xgs.mapper.EmployeeMapper;
import com.xgs.service.IEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}

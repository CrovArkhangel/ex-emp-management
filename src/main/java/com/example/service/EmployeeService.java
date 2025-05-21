package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員情報を操作するサービスクラスです.
 */
@Service
@Transactional
public class EmployeeService {
    /** employeesテーブルを操作するりぴじとりーです. */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全従業員の取得処理を行う.
     *
     * @return 全従業員情報
     */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }
}

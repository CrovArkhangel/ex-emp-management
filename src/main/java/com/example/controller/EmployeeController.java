package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

/**
 * 従業員情報を操作するコントローラーです.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    /** 従業員情報を操作するサービス */
    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧画面に遷移する.
     *
     * @param model モデル
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    /**
     * 従業員詳細画面に遷移する.
     *
     * @param id id
     * @param model モデル
     * @param form フォーム
     * @return 従業員情報詳細画面
     */
    @GetMapping("/showDetail")
    public String ShowDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "employee/detail";
    }

    /**
     * 従業員情報一覧画面に遷移する.
     *
     * @param form フォーム
     * @return 従業員一覧画面
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form){
        final Employee oldEmployeeInfo = employeeService.showDetail(Integer.parseInt(form.getId()));
        if(oldEmployeeInfo == null){
            return "employee/showList";
        }
        final String oldEmployeeImage = oldEmployeeInfo.getImage();
        final Employee newEmployeeInfo = new Employee();
        BeanUtils.copyProperties(form, newEmployeeInfo);
        if(Objects.equals(form.getImage(), "")){
            newEmployeeInfo.setImage(oldEmployeeImage);
        }

        employeeService.update(oldEmployeeInfo);
        return "redirect:/employee/showList";
    }
}

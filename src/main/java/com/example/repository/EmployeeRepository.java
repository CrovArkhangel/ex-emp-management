package com.example.repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * employeesテーブルを操作するリポジトリーです.
 */
@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private final int RECORDS_PER_PAGE = 10;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("image"),
            rs.getString("gender"),
            rs.getDate("hire_date"),
            rs.getString("mail_address"),
            rs.getString("zip_code"),
            rs.getString("address"),
            rs.getString("telephone"),
            rs.getInt("salary"),
            rs.getString("characteristics"),
            rs.getInt("dependents_count")
        );
        return employee;
    };

    /**
     * 全権検索を行う.
     *
     * @return 全従業員一覧
     */
    public List<Employee> findAll(String pageId){
        String sql = "select " +
                "id, " +
                "name, " +
                "image, " +
                "gender, " +
                "hire_date, " +
                "mail_address, " +
                "zip_code, " +
                "address, " +
                "telephone, " +
                "salary, " +
                "characteristics, " +
                "dependents_count " +
                "from employees order by hire_date desc LIMIT :recordsPerPage OFFSET :offset;";
        int offset = ( Integer.parseInt(pageId) - 1 ) * 10;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("offset", offset)
                .addValue("recordsPerPage", RECORDS_PER_PAGE);
        List<Employee> employees = template.query(sql, param, EMPLOYEE_ROW_MAPPER);
        return employees;
    }

    /**
     * 主キー検索を行う.
     *
     * @param id ID
     * @return 検索された従業員情報
     */
    public Employee findById(Integer id){
        String sql = "Select " +
                "id, " +
                "name, " +
                "image, " +
                "gender, " +
                "hire_date, " +
                "mail_address, " +
                "zip_code, " +
                "address, " +
                "telephone, " +
                "salary, " +
                "characteristics, " +
                "dependents_count " +
                " from employees where id = :id;";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /**
     * 渡した従業員情報を更新する.
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee){
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String sql = "Update employees set " +
                "name = :name, " +
                "image = :image, " +
                "gender = :gender, " +
                "hire_date = :hireDate, " +
                "mail_address = :mailAddress, " +
                "zip_code = :zipCode, " +
                "address = :address, " +
                "telephone = :telephone, " +
                "salary = :salary, " +
                "characteristics = :characteristics, " +
                "dependents_count = :dependentsCount " +
                "where id = :id;";
        template.update(sql, param);
    }
}

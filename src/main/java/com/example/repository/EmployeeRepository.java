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

import java.util.List;

/**
 * employeesテーブルを操作するリポジトリーです.
 */
@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

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
    public List<Employee> findAll(){
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
                "from employees order by hire_date desc;";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

        return employeeList;
    }

    /**
     * 主キー検索を行う.
     *
     * @param id ID
     * @return 検索された従業員情報
     */
    public Employee findById(String id){
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
                " from Administrators where id = :id;";
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
                "zip_code = zipCode, " +
                "address = :address, " +
                "telephone = :telephone, " +
                "salary = :salary, " +
                "characteristics = :characteristics, " +
                "dependentsCount = :dependents_count " +
                "where id = :id;";
        template.update(sql, param);
    }
}

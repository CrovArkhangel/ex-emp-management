package com.example.repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * administratorsテーブルを操作するリポジトリーです.
 *
 */
@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 渡した管理者情報をテーブルに挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = "insert into administrators(name, mail_address, password) values(:name, :mailAddress, :password);";
        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 取得した従業員情報
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "Select id, name, mail_address, password from Administrators where mail_address = :mailAddress AND password = :password;";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);
        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if(administratorList.isEmpty()){
            return null;
        }
        return administratorList.getFirst();
    }

    /**
     * idから管理者情報を取得する.
     *
     * @param id id
     * @return 取得した管理者情報
     */
    public Administrator finById(Integer id){
        System.out.println(id);
        final String sql = "select id, name, mail_address, password from administrators where id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if(administratorList.isEmpty()){
            return null;
        }
        return administratorList.getFirst();
    }

    /**
     * ログインしている管理者情報を更新する.
     *
     * @param administrator 管理者情報
     */
    public void update(Administrator administrator){
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        final String sql = "update administrators set name = :name, mail_address = :mailAddress, password = :password where id = :id";
        template.update(sql, param);
    }
}

package com.example.service;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者情報を操作するサービスクラスです.
 */
@Repository
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;


}

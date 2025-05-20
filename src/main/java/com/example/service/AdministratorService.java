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
    /** administrators テーブルを操作するリポジトリー*/
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を登録する業務処理を行う.
     *
     * @param administrator 登録する管理者情報
     */
    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }

    /**
     * ログイン処理をする.
     *
     * @param mailAddress 入力されたメールアドレス
     * @param password 入力されたパスワード
     * @return 検索結果
     */
    public Administrator login(String mailAddress, String password){
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

}

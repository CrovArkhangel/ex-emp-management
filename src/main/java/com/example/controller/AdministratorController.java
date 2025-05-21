package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者情報を操作するコントローラー.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    /** 管理者情報を操作するサービス */
    @Autowired
    private AdministratorService administratorService;

    /** session */
    @Autowired
    private HttpSession session;

    /**
     * ログイン画面に遷移する.
     *
     * @param form フォーム
     * @return ログイン画面
     */
    @GetMapping("")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }

    /**
     * ログイン処理を行う.
     *
     * @param form フォーム
     * @param model モデル
     * @return ログイン成功時：ログイン画面に遷移 ログイン失敗時：従業員一覧画面に遷移
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        String mailAddress = form.getMailAddress();
        String password = form.getPassword();
        Administrator administrator = administratorService.login(mailAddress,password);
        if(administrator == null){
            model.addAttribute("errorMessage","メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:employee/showList";
    }

    /**
     * ログイン画面に遷移する.
     *
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 管理者情報登録画面に遷移する.
     *
     * @param form フォーム
     * @return 管理者情報登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param form フォーム
     * @return ログイン画面
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }


}

package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ログインしている管理者情報を更新するフォームクラスです.
 */
public class UpdateAdministratorForm {
    /** id */
    private String id;
    /** 名前 */
    @NotBlank(message = "名前は必須です。")
    private String name;
    /** メールアドレス */
    @NotBlank(message = "メールアドレスは必須です。")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "メールアドレスの形式にしてください")
    private String mailAddress;
    /** パスワード */
    @NotBlank(message = "パスワードは必須です。")
    private String password;

    @Override
    public String toString() {
        return "InsertAdministratorForm{" +
                "name='" + name + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

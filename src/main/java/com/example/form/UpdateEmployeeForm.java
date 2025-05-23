package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 従業員情報更新時に使用するフォームクラスです.
 */
public class UpdateEmployeeForm {
    /** id */
    private String id;
    /** 名前 */
    @NotBlank(message = "名前は必須です。")
    private String name;
    /** 画像 */
    private String image;
    /** 性別 */
    @NotBlank(message = "性別は必須です。")
    private String gender;
    /** 入社日 */
    @NotNull(message = "入社日は必須です。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    /** メールアドレス */
    @NotBlank(message = "メールアドレスは必須です。")
    private String mailAddress;
    /** 郵便番号 */
    @NotBlank(message = "郵便番号は必須です")
    @Pattern(regexp = "^\\d{3}-\\d{4}$" , message = "郵便番号の形式にしてください。")
    private String zipCode;
    /** 住所 */
    @NotBlank(message = "住所は必須です。")
    private String address;
    /** 電話番号 */
    @NotBlank(message = "電話番号は必須です。")
    private String telephone;
    /** 給料 */
    @NotNull(message = "給料は必須です。")
    private Integer salary;
    /** 特性 */
    @NotBlank(message = "特性は必須です。")
    private String characteristics;
    /** 扶養人数 */
    @NotBlank(message = "不要人数は必須です。")
    private String dependentsCount;

    @Override
    public String toString() {
        return "UpdateEmployeeForm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                ", mailAddress='" + mailAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", characteristics='" + characteristics + '\'' +
                ", dependentsCount='" + dependentsCount + '\'' +
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
}

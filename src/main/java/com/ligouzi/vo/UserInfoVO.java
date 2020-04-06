package com.ligouzi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//只显示非空字段
public class UserInfoVO {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.userpic
     *
     * @mbg.generated
     */
    private String userpic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.question
     *
     * @mbg.generated
     */
    private String question;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.answer
     *
     * @mbg.generated
     */
    private String answer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.role
     *
     * @mbg.generated
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.create_time
     *
     * @mbg.generated
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.update_time
     *
     * @mbg.generated
     */
    private String updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column daka_user.ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.id
     *
     * @return the value of daka_user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.id
     *
     * @param id the value for daka_user.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.username
     *
     * @return the value of daka_user.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.username
     *
     * @param username the value for daka_user.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.email
     *
     * @return the value of daka_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.email
     *
     * @param email the value for daka_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.phone
     *
     * @return the value of daka_user.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.phone
     *
     * @param phone the value for daka_user.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.userpic
     *
     * @return the value of daka_user.userpic
     *
     * @mbg.generated
     */
    public String getUserpic() {
        return userpic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.userpic
     *
     * @param userpic the value for daka_user.userpic
     *
     * @mbg.generated
     */
    public void setUserpic(String userpic) {
        this.userpic = userpic == null ? null : userpic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.question
     *
     * @return the value of daka_user.question
     *
     * @mbg.generated
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.question
     *
     * @param question the value for daka_user.question
     *
     * @mbg.generated
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.answer
     *
     * @return the value of daka_user.answer
     *
     * @mbg.generated
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.answer
     *
     * @param answer the value for daka_user.answer
     *
     * @mbg.generated
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.role
     *
     * @return the value of daka_user.role
     *
     * @mbg.generated
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.role
     *
     * @param role the value for daka_user.role
     *
     * @mbg.generated
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.create_time
     *
     * @return the value of daka_user.create_time
     *
     * @mbg.generated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.create_time
     *
     * @param createTime the value for daka_user.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.update_time
     *
     * @return the value of daka_user.update_time
     *
     * @mbg.generated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.update_time
     *
     * @param updateTime the value for daka_user.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column daka_user.ip
     *
     * @return the value of daka_user.ip
     *
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column daka_user.ip
     *
     * @param ip the value for daka_user.ip
     *
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

}

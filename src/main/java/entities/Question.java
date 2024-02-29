/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Elisa Bothy
 */
public class Question {
    private static final long serialVersionUID = 1L;

    private Integer id_question;
    private String login;
    private String pwd;
    private int id_role;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append("id_person=").append(id_person);
        sb.append(", login=").append(login);
        sb.append(", pwd=").append(pwd);
        sb.append(", id_role=").append(id_role);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    @Override
    public Integer getId() {
        return this.id_person;
    }

    @Override
    public void setId(Integer id) {
        this.id_person = id;
    }
}

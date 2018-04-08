package ru.kpfu.itis.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.kpfu.itis.utils.validation.FieldMatch;

import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "passwordConfirmation", message = "The password fields must match")
public class UserForm {
    @NotEmpty(message = "Поле Никнейм не должно быть пустым")
    @Size(max = 10, message = "Максимальнвя длина никнейма не более 10 символов")
    private String nickname;
    @NotEmpty
    @Size(min = 5, message = "Длина пароля должна составлять не менее 5 символов")
    private String password;
    private String passwordConfirmation;
    private String firstname;
    private String surname;
    @Email
    private String email;

    public UserForm() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}

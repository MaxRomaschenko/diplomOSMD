package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_profile")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Введите пароль")
    @Size(min = 8,message = "Минимум 8 символов")
    @Column(name="password")
    private String password;
    @NotEmpty(message = "Введите email")
    @Email
    @Column(name="email")
    private String email;
    @NotEmpty(message = "Введите имя")
    @Size(min = 2, max = 25, message = "Минимум 2 символа")
    @Column(name="first_name")
    private String firstName;
    @NotEmpty(message = "Введите фамилию")
    @Size(min = 2, max = 25, message = "Минимум 2 символа")
    @Column(name="last_Name")
    private String lastName;
    @Size(max = 25)
    @Column(name="middle_Name")
    private String middleName;
    @NotNull(message = "Выберите роль пользователя")
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;
    @NotNull(message = "Выберите статус пользователя")
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
    @NotEmpty(message = "Введите номер телефона")
    @Column(name="phone")
    private String phone;
    @Size(max = 30)
    @Column(name="viber")
    private String viber;
    @Size(max = 30)
    @Column(name="telegram")
    private String telegram;
    @Column(name="created_at")
    private LocalDateTime created_at;
    @Column(name="updated_at")
    private LocalDateTime updated_at;

}

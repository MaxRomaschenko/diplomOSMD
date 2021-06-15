package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Введите пароль")
    @Column(name="title")
    private String title;

    @Column(name="message_text")
    private String messageText;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName  = "id")
    private User user;

}

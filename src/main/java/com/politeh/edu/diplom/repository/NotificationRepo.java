package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

}

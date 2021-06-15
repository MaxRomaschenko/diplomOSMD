package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Notification;
import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public Notification saveNotification(Notification notification){
        return notificationRepo.save(notification);
    }

    public Notification findById(Long id){
        return notificationRepo.getOne(id);
    }

    public void deleteById(Long id){
        notificationRepo.deleteById(id);
    }

    public List<Notification> findAll(){
        return notificationRepo.findAll();
    }


}

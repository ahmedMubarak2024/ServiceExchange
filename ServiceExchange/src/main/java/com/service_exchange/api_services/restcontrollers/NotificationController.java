/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.restcontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service_exchange.api_services.bussinesslayer.NotificationService;
import com.service_exchange.api_services.dao.dto.NotificationDto;
import com.service_exchange.utal.firebasenotificationsutil.FirebaseNotificationMessageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nouran
 */
@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET, value = "/notifications/{userId}/{pageNum}")
    private List<NotificationDto> getAllUserNotifications(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum) {
        if (userId != null) {
            return notificationService.getAllUserNotifications(userId, pageNum);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/makeNotification")
    private NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {

        return notificationService.createNotification(notificationDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/send")
    private boolean sendNotificationToUser(@RequestBody String myJson) {
        try {

            if (myJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(myJson);

                NotificationDto notificationDto = mapper.convertValue(node.get("notificationDto"), NotificationDto.class);

                List<Integer> usersIds = mapper.convertValue(node.get("users"), List.class);

                notificationService.sendNotificationToUser(notificationDto, usersIds);

                return true;
            }

        } catch (IOException ex) {
            Logger.getLogger(NotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notifications/sendFirebase")
    private boolean sendNotificationToUserFirebase(@RequestBody String myJson) {
        try {

            if (myJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(myJson);

                NotificationDto notificationDto = mapper.convertValue(node.get("notificationDto"), NotificationDto.class);
                System.out.println(notificationDto.getBody());
                String user = mapper.convertValue(node.get("userToken"), String.class);
                String authKey = mapper.convertValue(node.get("authKey"), String.class);
                FirebaseNotificationMessageMaker.sendFirebaseNotificationMessageToUserTry(notificationDto,"try", user, authKey);
                return true;
            }


            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

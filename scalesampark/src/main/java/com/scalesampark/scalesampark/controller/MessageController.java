package com.scalesampark.scalesampark.controller;

import com.scalesampark.scalesampark.model.MessageData;
import com.scalesampark.scalesampark.model.api.MessageRequest;
import com.scalesampark.scalesampark.service.MessageService;
import com.scalesampark.scalesampark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody MessageRequest messageRequest){
        //check if request is valid or not
        if (StringUtils.isEmpty(messageRequest.getMessage()) || StringUtils.isEmpty(messageRequest.getMessageType()) || StringUtils.isEmpty(messageRequest.getParticipant_uuid())){
            return ResponseEntity.badRequest().body("Insufficient request arguments");
        }

        //create a new message
        try
        {
            if(userService.isUserExist(messageRequest.getParticipant_uuid())){
                String uuid = messageService.addMessage(messageRequest);
                userService.setLastSeen(messageRequest.getParticipant_uuid(), Calendar.getInstance().getTime());
                return ResponseEntity.ok("Message added successfully");
            }
            else{
                return ResponseEntity.notFound().build();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again...");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{participant_uuid}")
    public ResponseEntity<?> getPendingMessages(@PathVariable String participant_uuid){
        //check if request is valid or not
        if(StringUtils.isEmpty(participant_uuid)){
            return ResponseEntity.badRequest().body("Invalid path parameter");
        }


        //first check if valid user or not
        if(userService.isUserExist(participant_uuid)){
            try
            {
                List<MessageData> messageDataList = messageService.getPendingMessages(userService.getLastFetched(participant_uuid));
                userService.setLastFetched(participant_uuid, Calendar.getInstance().getTime());
                userService.setLastSeen(participant_uuid, Calendar.getInstance().getTime());
                return new ResponseEntity<>(messageDataList, HttpStatus.OK);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again...");
            }
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}


package com.scalesampark.scalesampark.controller;

import com.scalesampark.scalesampark.model.api.RegistrationRequest;
import com.scalesampark.scalesampark.model.api.RegistrationResponse;
import com.scalesampark.scalesampark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    UserService userService;

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody RegistrationRequest registrationRequest){
        //check if request is valid or not
        if(StringUtils.isEmpty(registrationRequest.getEmail()) || StringUtils.isEmpty(registrationRequest.getNickname())){
            return ResponseEntity.badRequest().body("Insufficient request arguments");
        }

        // create a new account
        String uuid = userService.createUser(registrationRequest);
        if(StringUtils.isEmpty(uuid)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again...");
        }

        //create response body
        RegistrationResponse response = new RegistrationResponse();
        response.setParticipant_uuid(uuid);
        return new ResponseEntity<RegistrationResponse>(response, HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, path = "/{uuid}")
    public ResponseEntity<?> getAll(@PathVariable String uuid){
        //check if request is valid or not
        if(StringUtils.isEmpty(uuid)){
            return ResponseEntity.badRequest().body("Invalid path parameter");
        }

        //first check if valid user or not
        if(userService.isUserExist(uuid))
        {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid){
        //check if request is valid or not
        if(StringUtils.isEmpty(uuid)){
            return ResponseEntity.badRequest().body("Invalid path parameter");
        }

        //first check if valid user or not
        if(userService.isUserExist(uuid))
        {
            userService.removeUser(uuid);
            return ResponseEntity.ok("User removed successfully");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }


}

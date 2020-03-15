package com.scalesampark.scalesampark.service;

import com.scalesampark.scalesampark.data.User;
import com.scalesampark.scalesampark.model.UserData;
import com.scalesampark.scalesampark.model.api.RegistrationRequest;
import com.scalesampark.scalesampark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String createUser(RegistrationRequest requestData){
        User user = new User();

        user.setUniqueId(UUID.randomUUID().toString());
        user.setEmailId(requestData.getEmail());
        user.setUserId(requestData.getNickname());
        user.setLastSeen(Calendar.getInstance().getTime());
        user.setLastFetched(Calendar.getInstance().getTime());

        userRepository.save(user);

        return user.getUniqueId();
    }

    public void removeUser(String uuid){
        userRepository.deleteById(uuid);
    }

    public List<UserData> getAllUsers(){
        List<UserData> userDataList = new ArrayList<>();

        List<User> users = userRepository.findAll();
        users.stream().forEach(user -> {
            UserData userData = new UserData(user.getUniqueId(), user.getUserId(), user.getLastSeen());
            userDataList.add(userData);
        });

        return userDataList;
    }

    public User getUser(String uuid){
        return userRepository.getOne(uuid);
    }

    public boolean isUserExist(String uuid){
        return userRepository.existsById(uuid);
    }

    public Date getLastSeen(String uuid){
        User user = getUser(uuid);
        if(user != null)
        {
            return user.getLastSeen();
        }
        return null;
    }

    @Transactional
    public void setLastSeen(String uuid, Date date){
        User user = getUser(uuid);
        if(user != null)
        {
            user.setLastSeen(date);
            userRepository.save(user);
        }
    }

    public Date getLastFetched(String uuid){
        User user = getUser(uuid);
        if(user != null)
        {
            return user.getLastFetched();
        }
        return null;
    }

    @Transactional
    public void setLastFetched(String uuid, Date date){
        User user = getUser(uuid);
        if (user != null){
            user.setLastFetched(date);
            userRepository.save(user);
        }
    }
}

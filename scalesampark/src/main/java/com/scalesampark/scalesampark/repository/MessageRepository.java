package com.scalesampark.scalesampark.repository;

import com.scalesampark.scalesampark.data.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByArrivedAtAfter(Date arrivedAt);
}

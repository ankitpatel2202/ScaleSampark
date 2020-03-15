package com.scalesampark.scalesampark.data;

import com.scalesampark.scalesampark.utils.MessageType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
@Data
public class Message {
    @Id
    @Column(name = "UUID")
    private String uniqueId;

    @Column(name = "PARTICIPANT_UUID")
    private String userUniqueId;

    @Column(name = "MESSAGE_TYPE")
    private MessageType messageType;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ARRIVED_AT")
    private Date arrivedAt;
}


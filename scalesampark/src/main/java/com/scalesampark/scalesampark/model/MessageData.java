package com.scalesampark.scalesampark.model;

import com.scalesampark.scalesampark.utils.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageData {
    private String participant_uuid;
    private String message_uuid;
    private int messageType;
    private String message;
}

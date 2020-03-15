package com.scalesampark.scalesampark.model.api;

import com.scalesampark.scalesampark.utils.MessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequest {
    private String participant_uuid;
    private MessageType messageType;
    private String message;
}

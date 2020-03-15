package com.scalesampark.scalesampark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    String participant_uuid;
    String nickname;
    Date lastSeen;
}

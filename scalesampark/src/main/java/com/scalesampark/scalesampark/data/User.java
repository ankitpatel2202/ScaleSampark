package com.scalesampark.scalesampark.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "USER_TABLE")
@Data
public class User {
    @Id
    @Column(name = "UUID")
    private String uniqueId;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "LAST_SEEN")
    private Date lastSeen;

    @Column(name = "LAST_FETCHED")
    private Date lastFetched;
}

package org.commercial_real_estate.model.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Deal {

    private long id;
    private Date date;
    private long dealStatusId;
    private long objectId;
    private long tenantId;
    private long realtorId;
    private int price;
    private String dealStatus;
    private String objectAddress;
    private String tenantFullName;
    private String realtorFullName;
    private String buildingNumber;
    private int premiseNumber;
}

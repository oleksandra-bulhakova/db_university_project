package org.commercial_real_estate.model.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Demonstration {

    private long id;
    private Date date;
    private long demoStatusId;
    private long objectId;
    private long tenantId;
    private long realtorId;
    private String objectAddress;
    private String tenantFullName;
    private String realtorFullName;
    private String demoStatus;
}

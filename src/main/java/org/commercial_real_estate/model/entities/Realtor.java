package org.commercial_real_estate.model.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Realtor {

    private long id;
    private long streetId;
    private String buildingNumber;
    private int premiseNumber;
    private long workingStatusId;
    private long levelId;
    private long specializationId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private Date startDate;
    private String fullName;
    private String address;
    private String level;
    private String specialization;
    private String workingStatus;
    private String streetName;
}

package org.commercial_real_estate.model;


import lombok.Data;

@Data
public class Owner {
    private long id;
    private long streetId;
    private long acquisitionSourceId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private Data acquisitionDate;
    private String buildingNumber;
    private int premiseNumber;
}

package org.commercial_real_estate.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tenant {

    private long id;
    private long streetId;
    private long acquisitionSourceId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private Date acquisitionDate;
    private String buildingNumber;
    private int premiseNumber;
    private long desiredObjectTypeId;
    private long desiredDistrictId;
    private int budget;
    private int desiredArea;
    private String streetName;
    private String AcquisitionSourceName;
}

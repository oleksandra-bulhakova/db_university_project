package org.commercial_real_estate.model;

import lombok.Data;

@Data
public class RealEstateObject {

    private long id;
    private long streetId;
    private String streetName;
    private long ownerId;
    private long objectTypeId;
    private int area;
    private int floor;
    private boolean renovation;
    private boolean furniture;
    private int price;
    private String buildingNumber;
    private int premiseNumber;
}

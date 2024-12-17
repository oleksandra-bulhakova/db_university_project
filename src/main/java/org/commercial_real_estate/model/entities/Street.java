package org.commercial_real_estate.model.entities;


import lombok.Data;

@Data
public class Street {

    private long id;
    private long districtId;
    private String streetName;
}

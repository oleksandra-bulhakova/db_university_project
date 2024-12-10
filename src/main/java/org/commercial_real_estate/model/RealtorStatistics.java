package org.commercial_real_estate.model;

import lombok.Data;

@Data
public class RealtorStatistics {
    private String firstName;
    private String lastName;
    private int totalDeals;
    private double avgAmount;
    private double avgArea;
}

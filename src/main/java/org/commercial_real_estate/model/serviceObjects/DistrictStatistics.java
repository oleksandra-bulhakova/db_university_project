package org.commercial_real_estate.model.serviceObjects;

import lombok.Data;

@Data
public class DistrictStatistics {
    private String districtName;
    private int totalDeals;
    private double averageDealPrice;
    private double averageDemosPerDeal;
}

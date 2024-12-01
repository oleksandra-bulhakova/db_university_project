package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Deal;

import java.util.List;
import java.util.Map;

public interface DealDAO {

    public List<Deal> getAllDeals();
    public Deal getDealById(long id);
    public Map<Long, String> getObjects();
    public Map<Long, String> getTenants();
    public Map<Long, String> getStatuses();
    public Map<Long, String> getRealtors();
    public void updateDeal(Deal deal);
    public void createDeal(Deal deal);
    public void deleteDeal(long id);

}

package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Realtor;


import java.util.List;
import java.util.Map;

public interface RealtorDAO {

    public List<Realtor> getAllRealtors();
    public Realtor getRealtorById(long realtorId);
    public Map<Long, String> getStreets();
    public Map<Long, String> getSpecializations();
    public Map<Long, String> getWorkingStatuses();
    public Map<Long, String> getLevels();
    public void updateRealtor(Realtor realtor);

}

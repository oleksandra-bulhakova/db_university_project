package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.entities.Realtor;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public void createRealtor(Realtor realtor);
    public void deleteRealtor(long id) throws SQLIntegrityConstraintViolationException;
    public List<Realtor> searchRealtors(String searchQuery) throws SQLException;

}

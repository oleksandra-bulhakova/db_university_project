package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Demonstration;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface DemonstrationDAO {

    public List<Demonstration> getAllDemonstrations();
    public Demonstration getDemoById(long id);
    public Map<Long, String> getObjects();
    public Map<Long, String> getStatuses();
    public Map<Long, String> getTenants();
    public Map<Long, String> getRealtors();
    public void updateDemo(Demonstration demonstration);
    public void createDemo(Demonstration demonstration);
    public void deleteDemo(long id);
}

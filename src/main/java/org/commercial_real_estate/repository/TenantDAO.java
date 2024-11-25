package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Tenant;

import java.util.List;
import java.util.Map;

public interface TenantDAO {

    public List<Tenant> getAllTenants();
    public Tenant getTenantById(long id);
    public void updateTenant(Tenant tenant);
    public Map<Long, String> getStreets();
    public Map<Long, String> getAllAcquisitionSources();
    public Map<Long, String> getDistricts();
    public Map<Long, String> getObjectTypes();
}

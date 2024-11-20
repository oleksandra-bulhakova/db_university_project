package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Tenant;

import java.util.List;

public interface TenantDAO {

    public List<Tenant> getAllTenants();
}

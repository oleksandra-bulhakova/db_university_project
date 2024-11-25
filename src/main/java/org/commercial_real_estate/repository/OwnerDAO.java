package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Owner;

import java.util.List;
import java.util.Map;

public interface OwnerDAO {

    public List<Owner> getAllOwners();
    public boolean updateOwner(Owner owner);
    public Owner getOwnerById(long id);
    public List<Map<String, Object>> getAllAcquisitionSources();
    public List<Map<String, Object>> getAllStreets();
}

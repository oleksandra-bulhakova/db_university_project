package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.Owner;

import java.util.List;

public interface OwnerDAO {

    public List<Owner> getAllOwners();
    public boolean updateOwner(Owner owner);
}

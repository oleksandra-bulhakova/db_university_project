package org.commercial_real_estate.repository;

import org.commercial_real_estate.model.entities.RealEstateObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RealEstateObjectDAO {

    List<RealEstateObject> getAllRealEstateObjects();
    public RealEstateObject getRealEstateObjectById(long id);
    public void updateObject(RealEstateObject realEstateObject);
    public Map<Long, String> getStreets();
    public Map<Long, String> getObjectTypes();
    public Map<Long, String> getOwners();
    public void createObject(RealEstateObject realEstateObject);
    public void deleteObjectByd(long id) throws SQLException;
}

package com.prabuddha.poc.spring_hibernate.repository;

import com.prabuddha.poc.spring_hibernate.model.VendorDefectMapper;

public interface DataRepository {

    void save(VendorDefectMapper data);
    void delete(String vendorLookupId, String defectCode);

}

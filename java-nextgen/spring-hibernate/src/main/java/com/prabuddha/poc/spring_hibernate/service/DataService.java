package com.prabuddha.poc.spring_hibernate.service;

import com.prabuddha.poc.spring_hibernate.model.VendorDefectMapper;

public interface DataService {

    void addData(VendorDefectMapper data);
    void deleteData(String vendorLookupId, String defectCode);

}

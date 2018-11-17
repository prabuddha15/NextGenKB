package com.prabuddha.poc.spring_hibernate.service;

import com.prabuddha.poc.spring_hibernate.model.VendorDefectMapper;
import com.prabuddha.poc.spring_hibernate.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("dataService")
public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    @Autowired
    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    @Transactional
    public void addData(VendorDefectMapper data) {
        dataRepository.save(data);
    }

    @Override
    @Transactional
    public void deleteData(String vendorLookupId, String defectCode) {
        dataRepository.delete(vendorLookupId,defectCode);
    }
}

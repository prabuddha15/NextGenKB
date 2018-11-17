package com.prabuddha.poc.spring_hibernate.repository;

import com.prabuddha.poc.spring_hibernate.model.VendorDefectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("dataRepository")
public class DataRepositoryImpl implements DataRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(VendorDefectMapper player) {
        sessionFactory.getCurrentSession().save(player);
        System.out.println("Data is successfully added into the system ");
    }

    @Override
    public void delete(String vendorLookupId, String defectCode) {
        Query q = sessionFactory.getCurrentSession().createQuery("delete from VendorDefectMapper where VENDOR_LOOKUP_ID = '"+vendorLookupId+"' and DEFECT_CODE='"+defectCode+"'");
        q.executeUpdate();
    }
}

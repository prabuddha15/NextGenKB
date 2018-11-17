package com.prabuddha.poc.spring_hibernate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "VENDOR_DEFECT_MAPPING")
public class VendorDefectMapper implements Serializable {

    @EmbeddedId
    private VendorDefectId vendorDefectId;

    @Column(name = "STATUS_FLAG")
    private String statusFlag;

    @Column(name = "ADD_BY_ID")
    private String addById;

    @Column(name = "ADD_DATE")
    private Timestamp addDate;


    public VendorDefectMapper() {
        super();
    }

    public VendorDefectMapper(VendorDefectId vendorDefectId, String statusFlag, String addById, Timestamp addDate) {
        this.vendorDefectId = vendorDefectId;
        this.statusFlag = statusFlag;
        this.addById = addById;
        this.addDate = addDate;
    }

    public VendorDefectId getVendorDefectId() {
        return vendorDefectId;
    }

    public void setVendorDefectId(VendorDefectId vendorDefectId) {
        this.vendorDefectId = vendorDefectId;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getAddById() {
        return addById;
    }

    public void setAddById(String addById) {
        this.addById = addById;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "VendorDefectMapper{" +
                "vendorDefectId=" + vendorDefectId +
                ", statusFlag='" + statusFlag + '\'' +
                ", addById='" + addById + '\'' +
                ", addDate=" + addDate +
                '}';
    }
}

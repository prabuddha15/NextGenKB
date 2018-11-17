package com.prabuddha.poc.spring_hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VendorDefectId  implements Serializable {

    @Column(name="VENDOR_LOOKUP_ID")
    private String vendorLookupId;

    @Column(name = "DEFECT_CODE")
    private String defectCode;

    public VendorDefectId() {
        super();
    }

    public VendorDefectId(String vendorLookupId, String defectCode) {
        this.vendorLookupId = vendorLookupId;
        this.defectCode = defectCode;
    }

    public String getVendorLookupId() {
        return vendorLookupId;
    }

    public void setVendorLookupId(String vendorLookupId) {
        this.vendorLookupId = vendorLookupId;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorDefectId that = (VendorDefectId) o;
        return Objects.equals(vendorLookupId, that.vendorLookupId) &&
                Objects.equals(defectCode, that.defectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorLookupId, defectCode);
    }

    @Override
    public String toString() {
        return "VendorDefectId{" +
                "vendorLookupId='" + vendorLookupId + '\'' +
                ", defectCode='" + defectCode + '\'' +
                '}';
    }
}

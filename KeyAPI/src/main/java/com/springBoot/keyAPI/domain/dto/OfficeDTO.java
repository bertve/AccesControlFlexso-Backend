package com.springBoot.keyAPI.domain.dto;

import com.springBoot.keyAPI.domain.Address;
import com.springBoot.keyAPI.domain.Company;


public class OfficeDTO {
    private long officeId;
    private Address address;
    private CompanyDTO company;

    public OfficeDTO(long officeId, Address address, CompanyDTO company) {
        this.officeId = officeId;
        this.address = address;
        this.company = company;
    }

    public long getOfficeId() {
        return officeId;
    }

    public Address getAddress() {
        return address;
    }

    public CompanyDTO getCompany() {
        return company;
    }


}

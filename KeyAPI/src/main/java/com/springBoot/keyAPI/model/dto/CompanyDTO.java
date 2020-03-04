package com.springBoot.keyAPI.model.dto;


public class CompanyDTO {
    private long companyId;
    private String name;

    public CompanyDTO(long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public long getCompanyId() {
        return companyId;
    }


    public String getName() {
        return name;
    }

}

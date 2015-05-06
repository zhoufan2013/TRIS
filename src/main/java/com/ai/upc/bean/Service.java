package com.ai.upc.bean;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * Created by zhoufan on 15/5/5.
 */
public class Service {

    private String serviceName;

    private String serviceType;

    private String serviceCatagory;

    private String description;

    private List<CharSpec> servChar;

    /**
     * Map<relServiceId, relationship>
     */
    private Map<String, String> relServices;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceCatagory() {
        return serviceCatagory;
    }

    public void setServiceCatagory(String serviceCatagory) {
        this.serviceCatagory = serviceCatagory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getRelServices() {
        return relServices;
    }

    public void setRelServices(Map<String, String> relServices) {
        this.relServices = relServices;
    }

    public List<CharSpec> getServChar() {
        return servChar;
    }

    public void setServChar(List<CharSpec> servChar) {
        this.servChar = servChar;
    }
}

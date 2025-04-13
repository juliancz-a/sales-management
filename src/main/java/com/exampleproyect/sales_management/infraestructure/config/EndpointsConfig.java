package com.exampleproyect.sales_management.infraestructure.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="endpoints")
public class EndpointsConfig {

    private Map<String, String> getRequests;

    private Map<String, String> postRequests;

    private Map<String, String> putRequests;

    private Map<String, String> deleteRequests;


    public Map<String, String> getGetRequests() {
        return getRequests;
    }

    public void setGetRequests(Map<String, String> getRequests) {
        this.getRequests = getRequests;
    }


    public Map<String, String> getPostRequests() {
        return postRequests;
    }


    public void setPostRequests(Map<String, String> postRequests) {
        this.postRequests = postRequests;
    }


    public Map<String, String> getPutRequests() {
        return putRequests;
    }


    public void setPutRequests(Map<String, String> putRequests) {
        this.putRequests = putRequests;
    }


    public Map<String, String> getDeleteRequests() {
        return deleteRequests;
    }


    public void setDeleteRequests(Map<String, String> deleteRequests) {
        this.deleteRequests = deleteRequests;
    }

  

    public String[] obtainNoAuthRequests() {
        String requests = getGetRequests().get("noAuth") + "," + getPostRequests().get("noAuth");

        System.out.println(requests);
        return requests.split(",");
    }

    public String[] obtainAuthRequests() {
        String requests = getGetRequests().get("authRequired") + "," + getPostRequests().get("authRequired")
        + "," + getDeleteRequests().get("authRequired");
        return requests.split(",");
    }

    public String[] obtainAdminRequests() {
        String requests = getGetRequests().get("onlyAdmin") + "," + getPostRequests().get("onlyAdmin")
        + "," + getPutRequests().get("onlyAdmin") + "," + getDeleteRequests().get("onlyAdmin");

        System.out.println(requests);
        return requests.split(",");
    }

}

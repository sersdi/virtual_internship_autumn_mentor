module adapter_rest {

    requires core_api;
    requires core_services;
    requires adapter_dto;

    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.web;
    requires com.google.common;
    requires org.slf4j;

    exports org.javaguru.travel.insurance.rest.common;
    exports org.javaguru.travel.insurance.rest.v1;
    exports org.javaguru.travel.insurance.rest.v2;


}
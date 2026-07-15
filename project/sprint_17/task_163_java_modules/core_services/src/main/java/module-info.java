module core_services {

    requires core_api;
    requires core_domain;
    requires core_validations;
    requires core_underwriting;

    requires lombok;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    exports org.javaguru.travel.insurance.core.services;

}
module core_underwriting {

    requires core_api;
    requires core_utils;
    requires core_domain;
    requires core_repositories;

    requires lombok;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    exports org.javaguru.travel.insurance.core.underwriting;

}
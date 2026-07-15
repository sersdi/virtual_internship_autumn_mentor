module core_validations {

    requires core_api;
    requires core_utils;
    requires core_repositories;

    requires lombok;
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    exports org.javaguru.travel.insurance.core.validations;

}
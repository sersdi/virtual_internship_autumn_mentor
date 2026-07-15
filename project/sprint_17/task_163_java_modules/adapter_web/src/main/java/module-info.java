module adapter_web {

    requires core_api;
    requires core_services;
    requires adapter_dto;

    requires spring.context;
    requires spring.web;

    exports org.javaguru.travel.insurance.web.v1;
    exports org.javaguru.travel.insurance.web.v2;

}
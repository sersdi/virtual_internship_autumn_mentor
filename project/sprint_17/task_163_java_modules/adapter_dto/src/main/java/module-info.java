module adapter_dto {

    requires core_api;

    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires spring.boot;
    requires spring.context;

    exports org.javaguru.travel.insurance.dto;
    exports org.javaguru.travel.insurance.dto.util;
    exports org.javaguru.travel.insurance.dto.v1;
    exports org.javaguru.travel.insurance.dto.v2;

}
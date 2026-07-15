module core_repositories {

    requires core_domain;

    requires spring.data.jpa;
    requires spring.data.commons;

    exports org.javaguru.travel.insurance.core.repositories;

}
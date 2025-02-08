package com.az.edu.turing.msbooking.specification;

import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.model.dto.request.FlightFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecification {

    public static Specification<FlightEntity> filterFlights(FlightFilter filter) {
        return (Root<FlightEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.flightNumber() != null) {
                predicate = cb.equal(predicate, cb.equal(root.get("flightNumber"), filter.flightNumber()));
            }
            if (filter.availableSeats() != null) {
                predicate = cb.equal(predicate, cb.greaterThanOrEqualTo(root.get("availableSeats"), filter.availableSeats()));
            }
            if (filter.departureCity() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("departureCity"), filter.departureCity()));
            }
            if (filter.arrivalCity() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("arrivalCity"), filter.arrivalCity()));
            }
            if (filter.departureDateTime() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("departureDateTime"), filter.departureDateTime()));
            }
            if (filter.arrivalDateTime() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("arrivalDateTime"), filter.arrivalDateTime()));
            }
            if (filter.maxPrice() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice()));
            }

            return predicate;
        };
    }
}

package ru.kpfu.itis.model.specs;

import org.springframework.data.jpa.domain.Specification;
import ru.kpfu.itis.model.Driver;
import ru.kpfu.itis.model.Passenger;
import ru.kpfu.itis.model.Trip;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.utils.DateUtil;

import javax.persistence.criteria.*;
import java.util.*;

public class TripSpecs {
    public static Specification<Trip> checkParams(final User user, final Date date) {
        return new Specification<Trip>() {

            List<Predicate> predicates = new ArrayList<Predicate>();


            public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (date != null) {
                    Date previousMonth = DateUtil.getPreviousMonth(date);
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("date"), previousMonth));
                }
                if (user.getDriver() != null) {
                    predicates.add(cb.equal(root.<Driver>get("driver"), user.getDriver()));
                }
                if(user.getPassenger() != null) {
                    query.distinct(true);
                    Root<Passenger> passengerRoot = query.from(Passenger.class);
                    Expression<Collection<Trip>> tripsOfPassenger = passengerRoot.get("trips");
                    predicates.add(cb.and(cb.equal(passengerRoot.get("user").get("nickname"), user.getNickname()), cb.isMember(root, tripsOfPassenger)));

                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
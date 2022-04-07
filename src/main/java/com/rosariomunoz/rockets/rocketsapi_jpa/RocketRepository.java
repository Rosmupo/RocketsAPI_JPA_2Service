package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RocketRepository extends CrudRepository<Rocket, String> {
    @Query("SELECT propeller.rocket FROM propellers propeller GROUP BY propeller.rocket " +
            "HAVING SUM(propeller.currentPower) >= :currentPower")
    List<Rocket> findRocketsByTotalCurrentPower(
            @Param("currentPower") long currentPower);

    @Query("select p from rockets p " +
            "order by p.sumAllPowerPropellers desc")
    List<Rocket> findRocketsByOrderBySumAllPowerPropellers (@Param("sumAllPowerPropellers") long sumAllPowerPropellers);


}

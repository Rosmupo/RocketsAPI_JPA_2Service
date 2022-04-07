package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropellerRepository extends CrudRepository<Propeller, String> {

    List<Propeller> deleteAllByRocket(Rocket rocket);
}

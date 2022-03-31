package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PropellerRepository extends CrudRepository<Propeller, String> {

    List<Propeller> deleteAllByRocket(Rocket rocket);
}

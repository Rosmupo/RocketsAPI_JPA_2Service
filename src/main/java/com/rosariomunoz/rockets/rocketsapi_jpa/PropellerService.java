package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class PropellerService {
    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;


    public PropellerService(RocketRepository rocketRepository, PropellerRepository propellerRepository) {
        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;
    }

    public Propeller addPropeller(String rocketId, Propeller propeller) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        propeller = rocket.addPropeller(propeller);
        propellerRepository.save(propeller);
        return propeller;
    }

    public Rocket searchRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }

    public List<Propeller> getRocketPropellers(String rocketId) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        return rocket.getPropellerList();
    }

    public Propeller getPropeller(String propellerId) throws Exception {
        return propellerRepository.findById(propellerId).get();
    }


    public void deleteAllRocketPropellers(String rocketId) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        rocket.setSumAllPowerPropellers(0);
        rocketRepository.save(rocket);

        propellerRepository.deleteAllByRocket(rocket);

    }
    public void deleteOnePropeller(String rocketId, String propellerId) {
        propellerRepository.deleteById(propellerId);
    }

    public Propeller updatePropeller(String rocketId, String propellerId, Propeller info) throws Exception {
        Propeller propeller = getPropeller(propellerId);
        propeller.setMaxPower(info.getMaxPower());
        propellerRepository.save(propeller);
        return propeller;
    }
}

package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RocketService {
    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;

    public RocketService(RocketRepository rocketRepository, PropellerRepository propellerRepository) {
        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;
    }

    public Rocket createRocket(Rocket rocket) {
        this.rocketRepository.save(rocket);
        return rocket;
    }

    public Propeller addPropeller(String rocketId, Propeller propeller) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        propeller = rocket.addPropeller(propeller);
        propellerRepository.save(propeller);
        return propeller;
    }

    Rocket searchRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }

    public List<Propeller> getRocketPropellers(String rocketId) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        return rocket.getPropellerList();
    }

    public Propeller getPropeller(String propellerId) throws Exception {
        return propellerRepository.findById(propellerId).get();
    }
    @Transactional
    public void deleteAllRocketPropellers(String rocketId) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        rocket.setSumAllPowerPropellers(0);
        rocketRepository.save(rocket);

       propellerRepository.deleteAllByRocket(rocket);

    }


    public Rocket updateRocket(String rocketId, Rocket info) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        rocket.setRocketCode(info.getRocketCode());
        rocketRepository.save(rocket);

        return rocket;
    }

    public List<Rocket> getRockets() throws Exception {
        List<Rocket> rockets = new ArrayList<>();
        this.rocketRepository.findAll().forEach(rockets::add);
        return rockets;
    }

    public void deleteAllRockets() {
        rocketRepository.deleteAll();
    }

    public void deleteOneRocket(String rocketId) {
        rocketRepository.deleteById(rocketId);
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


    public Rocket speedUpRocket(String rocketId, int times) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        for (int i = 0; i < times; i++) {
        rocket.addSpeedUp();}
        rocketRepository.save(rocket);
        return rocket;



    }
    public Rocket slowDownRocket(String rocketId, int times) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        for (int i = 0; i < times; i++) {
        rocket.slowDown();}
        rocketRepository.save(rocket);
        return rocket;
    }

}

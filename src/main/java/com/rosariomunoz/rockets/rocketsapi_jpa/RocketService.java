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




    public Rocket speedUpRocket(String rocketId, int times) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        for (int i = 0; i < times; i++) {
            rocket.addSpeedUp();
        }
        rocketRepository.save(rocket);
        return rocket;


    }

    public Rocket slowDownRocket(String rocketId, int times) throws Exception {
        Rocket rocket = searchRocket(rocketId);
        for (int i = 0; i < times; i++) {
            rocket.slowDown();
        }
        rocketRepository.save(rocket);
        return rocket;
    }
    Rocket searchRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }


}

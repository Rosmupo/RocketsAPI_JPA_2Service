package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService rocketService;
    private PropellerService propellerService;
    private RocketRepository rocketRepository;


    public RocketRestController(RocketService rocketService, PropellerService propellerService, RocketRepository rocketRepository) {
        this.rocketService = rocketService;
        this.propellerService = propellerService;
        this.rocketRepository = rocketRepository;

    }

    @PostMapping("/rockets")
    public Rocket createRocket(@RequestBody Rocket rocket) {
        return rocketService.createRocket(rocket);
    }

    @GetMapping("/rockets")
    public List<Rocket> getRockets() throws Exception {
        return rocketService.getRockets();
    }

    @DeleteMapping("/rockets")
    public void deleteAllRockets() {
        rocketService.deleteAllRockets();
    }

    @DeleteMapping("/rockets/{rocketId}")
    public void deleteOneRocket(@PathVariable String rocketId) {
        rocketService.deleteOneRocket(rocketId);
    }

    @GetMapping("/rockets/{rocketId}")
    public Rocket searchRocket(@PathVariable String rocketId) throws Exception {
        return propellerService.searchRocket(rocketId);

    }

    @PutMapping("/rockets/{rocketId}")
    private Rocket updateRocket(@PathVariable String rocketId, @RequestBody Rocket rocket) throws Exception {
        return rocketService.updateRocket(rocketId, rocket);
    }

    @PostMapping("/rockets/{rocketId}/propellers")
    public Propeller addPropeller(@PathVariable String rocketId, @RequestBody Propeller propeller) throws Exception {
        return propellerService.addPropeller(rocketId, propeller);

    }

    @GetMapping("/rockets/{rocketId}/propellers")
    public List<Propeller> propellerList(@PathVariable String rocketId) throws Exception {
        return propellerService.getRocketPropellers(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers")
    public void deleteAllRocketPropellers(@PathVariable String rocketId) throws Exception {
        propellerService.deleteAllRocketPropellers(rocketId);
    }


    @GetMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller getPropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        return propellerService.getPropeller(propellerId);

    }

    @PutMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller updatePropeller(@PathVariable String rocketId, @PathVariable String propellerId, @RequestBody Propeller propeller) throws Exception {
        return propellerService.updatePropeller(rocketId, propellerId, propeller);

    }

    @DeleteMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void deleteOnePropeller(@PathVariable String rocketId, @PathVariable String propellerId) {
        propellerService.deleteOnePropeller(rocketId, propellerId);
    }

    @PostMapping("/rockets/{rocketId}/throttle")
    public Rocket speedUpRocket(@PathVariable String rocketId, @RequestBody String jsonString) throws Exception {
        JSONObject json = new JSONObject(jsonString);
        int times = json.getInt("timesPressAccelerate");
        return rocketService.speedUpRocket(rocketId, times);
    }


    @PostMapping("/rockets/{rocketId}/brake")
    public Rocket slowDownRocket(@PathVariable String rocketId, @RequestBody String jsonString) throws Exception {
        JSONObject json = new JSONObject(jsonString);
        int times = json.getInt("timesPressBrake");
        return rocketService.slowDownRocket(rocketId, times);
    }
    @GetMapping("/rockets/byTotalCurrentPower/{currentPower}")
    public List<Rocket> findRocketsByTotalCurrentPower
            (@PathVariable long currentPower){
        return rocketRepository.
                findRocketsByTotalCurrentPower(currentPower);
    }
    @GetMapping("/rockets/BySumAllPowerPropellers/{sumAllPowerPropellers}")
    public List<Rocket> findRocketsByOrderBySumAllPowerPropellers
            (@PathVariable long sumAllPowerPropellers){
        return rocketRepository.findRocketsByOrderBySumAllPowerPropellers(sumAllPowerPropellers);

    }



}

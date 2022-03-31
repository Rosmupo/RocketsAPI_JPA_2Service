package com.rosariomunoz.rockets.rocketsapi_jpa;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService rocketService;

    public RocketRestController(RocketService rocketService) {
        this.rocketService = rocketService;
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
        return rocketService.searchRocket(rocketId);

    }

    @PutMapping("/rockets/{rocketId}")
    private Rocket updateRocket(@PathVariable String rocketId, @RequestBody Rocket rocket) throws Exception {
        return rocketService.updateRocket(rocketId, rocket);
    }

    @PostMapping("/rockets/{rocketId}/propellers")
    public Propeller addPropeller(@PathVariable String rocketId, @RequestBody Propeller propeller) throws Exception {
        return rocketService.addPropeller(rocketId, propeller);

    }

    @GetMapping("/rockets/{rocketId}/propellers") //FALLA
    public List<Propeller> propellerList(@PathVariable String rocketId) throws Exception {
        return rocketService.getRocketPropellers(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers")// FALLA
    public void deleteAllRocketPropellers(@PathVariable String rocketId) throws Exception {
         rocketService.deleteAllRocketPropellers(rocketId);
    }


    @GetMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller getPropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        return rocketService.getPropeller(propellerId);

    }

    @PutMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller updatePropeller(@PathVariable String rocketId, @PathVariable String propellerId, @RequestBody Propeller propeller) throws Exception {
        return rocketService.updatePropeller(rocketId, propellerId, propeller);

    }

    @DeleteMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void deleteOnePropeller(@PathVariable String rocketId, @PathVariable String propellerId) {
        rocketService.deleteOnePropeller(rocketId, propellerId);
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

}

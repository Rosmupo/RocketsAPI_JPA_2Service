package com.rosariomunoz.rockets.rocketsapi_jpa;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "rockets")
public class Rocket {
    @Id
    private String rocketId = UUID.randomUUID().toString();

    private String rocketCode;
    private int sumAllPowerPropellers = 0;

    @OneToMany(mappedBy = "rocket")

    private List<Propeller> propellerList = new ArrayList<>();

    public Rocket() {

    }

    public int getSumAllPowerPropellers() {
        return sumAllPowerPropellers;
    }

    public void setSumAllPowerPropellers(int sumAllPowerPropellers) {
        this.sumAllPowerPropellers = sumAllPowerPropellers;
    }

    public Rocket(String rocketCode) throws Exception {
        checkRocketCode(rocketCode);
        this.rocketCode = rocketCode;
    }

    private void checkRocketCode(String rocketCode) throws Exception {
        if (rocketCode.length() < 8 | rocketCode.length() > 8)
            throw new Exception("el código del cohete debe tener ocho carácteres");
    }

    public String getRocketCode() {
        return rocketCode;
    }

    public void setRocketCode(String rocketCode) throws Exception {
        checkRocketCode(rocketCode);
        this.rocketCode = rocketCode;
    }

    public List<Propeller> getPropellerList() {
        return propellerList;
    }

    public void setPropellerList(List<Propeller> propellerList) {
        this.propellerList = propellerList;
    }

    public Propeller addPropeller(Propeller propeller) {
        propeller.setRocket(this);
        this.propellerList.add(propeller);
        return propeller;

    }

    public Propeller searchPropeller(String propellerId) throws Exception {
        for (Propeller currentPropeller : propellerList) {
            if (currentPropeller.getpropellerId().equals(propellerId)) {
                return currentPropeller;
            }
        }
        throw new Exception("No se ha encontrado el propulsor");
    }

    public String getrocketId() {
        return rocketId;
    }

    public void addSpeedUp() throws Exception {
        for (Propeller currentPropeller : propellerList) {
            sumAllPowerPropellers += currentPropeller.increasePower();
            checksumAllPowerPropellers();
        }

    }


    public void slowDown() throws Exception {
        for (Propeller currentPropeller : propellerList) {
            sumAllPowerPropellers -= currentPropeller.reducePower();
            checksumAllPowerPropellers();
        }
    }

    public void checksumAllPowerPropellers() throws Exception {
        int checkMaxPower = 0;
        for (Propeller currentPropeller : propellerList) {
            checkMaxPower += currentPropeller.getMaxPower();
        }
        if (checkMaxPower < sumAllPowerPropellers) {
            throw new Exception("la suma total de los propulsores no puede ser mayor a la suma de la máxima potencia de los propulsores");
        }
        if (sumAllPowerPropellers < 0) {
            throw new Exception("la suma de todas los propulsores no puede ser menor que 0");
        }
    }
}



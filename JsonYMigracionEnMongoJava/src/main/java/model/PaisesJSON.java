package model;

import java.util.List;
/**
 * Esta clase se usa para deserializar la información recibida de una url de paises en formato JSON
 * @author Bruno Tarditi
 */
public class PaisesJSON {
    private String name;
    private List<String> callingCodes;
    private String capital;
    private String region;
    private long population;
    private List<Double> latlng;

    public PaisesJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }



    @Override
    public String toString() {
        return "Entity.PaisesJSON{" +
                "name='" + name + '\'' +
                ", callingCodes=" + callingCodes +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", latlng=" + latlng +
                '}';
    }
}

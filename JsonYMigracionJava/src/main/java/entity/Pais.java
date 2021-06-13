package entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Se crea la clase Pais que será la tabla en la base de datos
 * @author Bruno Tarditi
 */
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_pais", nullable = false)
    private int codigoPais;

    @Column(name = "nombre_pais", length = 65, nullable = false)
    private String nombrePais;

   @Column(name = "capital_pais", length = 50, nullable = false)
    private String capitalPais;

    @Column(name = "region", length = 50, nullable = false)
    private String region;

    @Column(name = "poblacion")
    private long poblacion;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;

    public Pais() {
    }

    /**
     * Este constructor servirá para buscar por el codigo del país y verificar que no se repita
     * @param codigoPais int
     */
    public Pais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getCapitalPais() {
        return capitalPais;
    }

    public void setCapitalPais(String capitalPais) {
        this.capitalPais = capitalPais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(long poblacion) {
        this.poblacion = poblacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}

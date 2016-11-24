package como.isil.mynotes.rest.entity;

import java.io.Serializable;

/**
 * Created by Carlos Barrenechea on 23/11/2016.
 */

public class OrderEntity implements Serializable {



    private String id_order;
    private String detail;
    private String id_vehiculo;
    private String weight;
    private String RUC;
    private String origenlat;
    private String origenlon;
    private String destinolat;
    private String destinolon;

    public OrderEntity() {
    }

    public OrderEntity(String id_order, String detail, String id_vehiculo, String weight, String RUC, String origenlat, String origenlon, String destinolat, String destinolon) {
        this.id_order = id_order;
        this.detail = detail;
        this.id_vehiculo = id_vehiculo;
        this.weight = weight;
        this.RUC = RUC;
        this.origenlat = origenlat;
        this.origenlon = origenlon;
        this.destinolat = destinolat;
        this.destinolon = destinolon;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getOrigenlat() {
        return origenlat;
    }

    public void setOrigenlat(String origenlat) {
        this.origenlat = origenlat;
    }

    public String getOrigenlon() {
        return origenlon;
    }

    public void setOrigenlon(String origenlon) {
        this.origenlon = origenlon;
    }

    public String getDestinolat() {
        return destinolat;
    }

    public void setDestinolat(String destinolat) {
        this.destinolat = destinolat;
    }

    public String getDestinolon() {
        return destinolon;
    }

    public void setDestinolon(String destinolon) {
        this.destinolon = destinolon;
    }
}

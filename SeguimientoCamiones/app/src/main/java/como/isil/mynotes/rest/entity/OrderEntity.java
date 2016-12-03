package como.isil.mynotes.rest.entity;

import java.io.Serializable;

/**
 * Created by Carlos Barrenechea on 23/11/2016.
 */

public class OrderEntity implements Serializable {
    private String objectId;
    private String detail;
    private String id_vehiculo;
    private String weight;
    private String client_RUC;
    private String origen_lat;
    private String origen_lon;
    private String destino_lat;
    private String destino_lon;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderEntity(String detail, String id_vehiculo, String weight, String client_RUC, String origen_lat, String origen_lon, String destino_lat, String destino_lon, int status) {
        this.detail = detail;
        this.id_vehiculo = id_vehiculo;
        this.weight = weight;
        this.client_RUC = client_RUC;
        this.origen_lat = origen_lat;
        this.origen_lon = origen_lon;
        this.destino_lat = destino_lat;
        this.destino_lon = destino_lon;
        this.status = status;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public String getClient_RUC() {
        return client_RUC;
    }

    public void setClient_RUC(String client_RUC) {
        this.client_RUC = client_RUC;
    }

    public String getOrigen_lat() {
        return origen_lat;
    }

    public void setOrigen_lat(String origen_lat) {
        this.origen_lat = origen_lat;
    }

    public String getOrigen_lon() {
        return origen_lon;
    }

    public void setOrigen_lon(String origen_lon) {
        this.origen_lon = origen_lon;
    }

    public String getDestino_lat() {
        return destino_lat;
    }

    public void setDestino_lat(String destino_lat) {
        this.destino_lat = destino_lat;
    }

    public String getDestino_lon() {
        return destino_lon;
    }

    public void setDestino_lon(String destino_lon) {
        this.destino_lon = destino_lon;
    }

    public OrderEntity() {
    }


}

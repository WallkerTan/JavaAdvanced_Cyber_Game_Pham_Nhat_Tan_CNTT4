package Cyber_Gaming.unity;

import java.sql.Timestamp;
import Cyber_Gaming.unity.enums.AreaType;
import Cyber_Gaming.unity.enums.PCStatus;

public class pcs {
    public static pcs curentPcs = null;
    private int pcId;
    private String pcNumber;
    private AreaType area;
    private String configuration;
    private double pricePerHour;
    private PCStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public pcs() {}

    public pcs(int pcId, String pcNumber, AreaType area, String configuration, double pricePerHour,
            PCStatus status, Timestamp createdAt, Timestamp updatedAt) {
        this.pcId = pcId;
        this.pcNumber = pcNumber;
        this.area = area;
        this.configuration = configuration;
        this.pricePerHour = pricePerHour;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public String getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(String pcNumber) {
        this.pcNumber = pcNumber;
    }

    public AreaType getArea() {
        return area;
    }

    public void setArea(AreaType area) {
        this.area = area;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public PCStatus getStatus() {
        return status;
    }

    public void setStatus(PCStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | %-8s | %-12s | %-30s | %,8.0fđ | %s", pcId, pcNumber, area,
                configuration.length() > 30 ? configuration.substring(0, 27) + "..."
                        : configuration,
                pricePerHour, status.toString());
    }

}

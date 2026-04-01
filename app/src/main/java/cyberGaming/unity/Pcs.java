package cyberGaming.unity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import cyberGaming.unity.enums.AreaType;
import cyberGaming.unity.enums.PCStatus;

public class Pcs {
    public static Pcs curentPcs = null;
    private int pcId;
    private String pcNumber;
    private AreaType area;
    private String configuration;
    private double pricePerHour;
    private PCStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private static int int_id = 10;

    public Pcs() {}

    public Pcs(AreaType area, String configuration, double pricePerHour) {
        this.pcNumber = "PC" + (int_id + 1);
        int_id++;
        this.area = area;
        this.configuration = configuration;
        this.pricePerHour = pricePerHour;
        this.status = PCStatus.AVAILABLE;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
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

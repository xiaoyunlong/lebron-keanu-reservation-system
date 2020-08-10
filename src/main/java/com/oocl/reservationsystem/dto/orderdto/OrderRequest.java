package com.oocl.reservationsystem.dto.orderdto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderRequest {
    @NotNull
    private Integer carId;

    @NotNull
    private Integer customerId;

    @NotNull
    private Date startTime;

    @NotNull
    private Integer preCost;

    @NotNull
    private Integer parkingPositionId;

    public OrderRequest(@NotNull Integer carId, @NotNull Integer customerId,
                        @NotNull Date startTime, @NotNull Integer preCost,
                        @NotNull Integer parkingPositionId) {
        this.carId = carId;
        this.customerId = customerId;
        this.startTime = startTime;
        this.preCost = preCost;
        this.parkingPositionId = parkingPositionId;
    }

    public OrderRequest() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getPreCost() {
        return preCost;
    }

    public void setPreCost(Integer preCost) {
        this.preCost = preCost;
    }

    public Integer getParkingPositionId() {
        return parkingPositionId;
    }

    public void setParkingPositionId(Integer parkingPositionId) {
        this.parkingPositionId = parkingPositionId;
    }
}

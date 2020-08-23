package com.chengzzz.stepservice.entity;

public class listdata {
    private Integer active =1;
    private int calories;
    private Integer dataSource=2;
    private String deviceId="M_NULL";
    private int distance;
    private Integer isUpload = 0;
    private String measurementTime;
    private Integer priority=0;
    private int step;
    private Integer type=2;
    private long updated;
    private int userId;
    private Integer DataSource = 2;
    private Integer exerciseTime=0;
    private String dayMeasurementTime;

    public String getDayMeasurementTime() {
        return dayMeasurementTime;
    }

    public void setDayMeasurementTime(String dayMeasurementTime) {
        this.dayMeasurementTime = dayMeasurementTime;
    }


    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Integer exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

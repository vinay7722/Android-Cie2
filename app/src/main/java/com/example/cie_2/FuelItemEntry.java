package com.example.cie_2;

public class FuelItemEntry {
    private String date, volume, rupeesPerLite, previousOdometerReading, currentOdometerReading, average;

    public FuelItemEntry(String date, String volume, String rupeesPerLite, String previousOdometerReading, String currentOdometerReading, String average) {
        this.date = date;
        this.volume = volume;
        this.rupeesPerLite = rupeesPerLite;
        this.previousOdometerReading = previousOdometerReading;
        this.currentOdometerReading = currentOdometerReading;
        this.average = average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRupeesPerLite() {
        return rupeesPerLite;
    }

    public void setRupeesPerLite(String rupeesPerLite) {
        this.rupeesPerLite = rupeesPerLite;
    }

    public String getCurrentOdometerReading() {
        return currentOdometerReading;
    }

    public void setCurrentOdometerReading(String currentOdometerReading) {
        this.currentOdometerReading = currentOdometerReading;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getPreviousOdometerReading() {
        return previousOdometerReading;
    }

    public void setPreviousOdometerReading(String previousOdometerReading) {
        this.previousOdometerReading = previousOdometerReading;
    }
}

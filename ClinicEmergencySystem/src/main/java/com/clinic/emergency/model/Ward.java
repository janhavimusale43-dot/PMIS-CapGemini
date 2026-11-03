package com.clinic.emergency.model;

public record Ward(int wardId, String wardName, int totalBeds, int occupiedBeds) {
    public int availableBeds() {
        return totalBeds - occupiedBeds;
    }

    public double utilizationPercentage() {
        return totalBeds == 0 ? 0.0 : occupiedBeds * 100.0 / totalBeds;
    }
}

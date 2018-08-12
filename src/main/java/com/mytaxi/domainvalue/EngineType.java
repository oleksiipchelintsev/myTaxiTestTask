package com.mytaxi.domainvalue;

public enum EngineType {
    ELECTRIC("ELECTRIC"),
    GAS("GAS"),
    PATROL("PATROL"),
    DIESEL("DIESEL");
    private final String engineType;

    EngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }
}

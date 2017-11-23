package com.mytaxi.domainvalue;

public enum Manufacturer
{
    AUDI("Audi"),
    BMW("BMW"),
    FORD_WERKE("Ford-Werke"),
    MERCEDES_BENZ("Mercedes-Benz"),
    OPEL("Opel"),
    PORSCHE("Porsche"),
    VOLKSWAGEN("Volkswagen"),
    OTHER("Other");

    private String manufacterName;


    Manufacturer(String manufacterName)
    {
        this.manufacterName = manufacterName;
    }


    public String getManufacterName()
    {
        return manufacterName;
    }
}

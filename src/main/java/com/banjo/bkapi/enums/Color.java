package com.banjo.bkapi.enums;

public enum Color {
    Blue("blue"),
    Green("green"),
    Purple("purple"),
    Orange("orange"),
    Yellow("yellow");

    private final String colorString;

    Color(String colorString){
        this.colorString =  colorString;
    }

    public String getColorString(){
        return this.colorString;
    }

    public static Color getColorFromString(String colorString) throws IllegalArgumentException {
        for(Color color : Color.values()){
            if(color.getColorString().equals(colorString)) return color;
        }
        throw new IllegalArgumentException("Color not found: " + colorString);
    }
}

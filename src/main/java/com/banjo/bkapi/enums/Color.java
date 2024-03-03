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
        return switch (colorString) {
            case "yellow" -> Color.Yellow;
            case "blue" -> Color.Blue;
            case "green" -> Color.Green;
            case "purple" -> Color.Purple;
            case "orange" -> Color.Orange;
            default -> throw new IllegalArgumentException("Color not found: " + colorString);
        };
    }
}

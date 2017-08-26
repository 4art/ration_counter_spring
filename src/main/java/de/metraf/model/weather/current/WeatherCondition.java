package de.metraf.model.weather.current;

public class WeatherCondition {
    private String text;
    private String icon;
    private int code;

    public WeatherCondition(String text, String icon, int code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    public WeatherCondition() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
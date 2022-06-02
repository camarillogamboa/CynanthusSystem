package edu.cynanthus.dayi.domain;

public interface Theme {

    Theme DARK_THEME = create("bg-black", "bg-dark", "border-dark", "text-white");
    Theme LIGHT_THEME = create("bg-white", "bg-light", "", "");

    String getBackground();

    String getPaneColor();

    String getBorderColor();

    String getTextColor();

    static Theme create(String background, String paneColor, String borderColor, String textColor) {
        return new ThemeBase(background, paneColor, borderColor, textColor);
    }

}

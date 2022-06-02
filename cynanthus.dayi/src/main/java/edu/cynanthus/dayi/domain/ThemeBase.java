package edu.cynanthus.dayi.domain;

import java.util.Objects;

class ThemeBase implements Theme {

    private final String background;
    private final String paneColor;

    private final String borderColor;
    private final String textColor;

    ThemeBase(String background, String paneColor, String borderColor, String textColor) {
        this.background = background;
        this.paneColor = paneColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
    }

    @Override
    public String getBackground() {
        return background;
    }

    @Override
    public String getPaneColor() {
        return paneColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public String getTextColor() {
        return textColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeBase themeBase = (ThemeBase) o;
        return Objects.equals(background, themeBase.background)
            && Objects.equals(paneColor, themeBase.paneColor)
            && Objects.equals(borderColor, themeBase.borderColor)
            && Objects.equals(textColor, themeBase.textColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(background, paneColor, borderColor, textColor);
    }

    @Override
    public String toString() {
        return "{" +
            "background:'" + background + '\'' +
            ",paneColor:'" + paneColor + '\'' +
            ",borderColor:'" + borderColor + '\'' +
            ",textColor:'" + textColor + '\'' +
            '}';
    }

}

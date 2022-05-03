package edu.cynanthus.auri.server.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InstructionValueConverter implements AttributeConverter<int[], String> {

    @Override
    public String convertToDatabaseColumn(int[] attribute) {
        StringBuilder string = new StringBuilder();
        for (int value : attribute) string.append(value);
        return string.toString();
    }

    @Override
    public int[] convertToEntityAttribute(String dbData) {
        int[] array = new int[dbData.length()];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt("" + dbData.charAt(i));
        return array;
    }

}

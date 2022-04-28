package edu.cynanthus.auri.server.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * El tipo Instruction value converter.
 */
@Converter
public class InstructionValueConverter implements AttributeConverter<int[], String> {

    /**
     * Convert to database column string.
     *
     * @param attribute el attribute
     * @return el string
     */
    @Override
    public String convertToDatabaseColumn(int[] attribute) {
        StringBuilder string = new StringBuilder();
        for (int value : attribute) string.append(value);
        return string.toString();
    }

    /**
     * Convert to entity attribute int [ ].
     *
     * @param dbData el db data
     * @return el int [ ]
     */
    @Override
    public int[] convertToEntityAttribute(String dbData) {
        int[] array = new int[dbData.length()];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt("" + dbData.charAt(i));
        return array;
    }

}

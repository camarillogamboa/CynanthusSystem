package edu.cynanthus.common;

import java.time.format.DateTimeFormatter;

/**
 * La interface Time patterns.
 */
public interface TimePatterns {

    /**
     * La constante FILE_NAME_PATTERN.
     */
    DateTimeFormatter FILE_NAME_PATTERN = DateTimeFormatter.ofPattern("HH_mm_ss.SS");
    /**
     * La constante DIRECTORY_NAME_PATTERN.
     */
    DateTimeFormatter DIRECTORY_NAME_PATTERN = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    /**
     * La constante LOG_FILE_NAME_PATTERN.
     */
    DateTimeFormatter LOG_FILE_NAME_PATTERN = DateTimeFormatter.ofPattern("dd_MM_yyyy-HH_mm_ss.SS");

}

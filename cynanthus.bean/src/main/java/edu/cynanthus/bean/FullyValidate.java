package edu.cynanthus.bean;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * La interface Fully validate.
 */
@GroupSequence(value = {Default.class, Required.class, ValidInfo.class})
public interface FullyValidate {
}

package edu.cynanthus.bean;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * El tipo Bean validation.
 */
public final class BeanValidation {

    /**
     * La constante DEFAULT_VALIDATOR.
     */
    private static final Validator DEFAULT_VALIDATOR;

    static {

        try (
            ValidatorFactory factory =
                Validation.byDefaultProvider().configure().messageInterpolator(
                    new ParameterMessageInterpolator()
                ).buildValidatorFactory()
        ) {
            DEFAULT_VALIDATOR = factory.getValidator();
        }
    }

    /**
     * Instancia un nuevo Bean validation.
     */
    private BeanValidation() {
    }

    /**
     * Validate set.
     *
     * @param <T>    el parámetro de tipo
     * @param bean   el bean
     * @param groups el groups
     * @return el set
     */
    public static <T extends Bean> Set<ConstraintViolation<T>> validate(T bean, Class<?>... groups) {
        return DEFAULT_VALIDATOR.validate(bean, groups);
    }

    /**
     * Is valid boolean.
     *
     * @param bean   el bean
     * @param groups el groups
     * @return el boolean
     */
    public static boolean isValid(Bean bean, Class<?>... groups) {
        return validate(bean, groups).isEmpty();
    }

    /**
     * Fully validate boolean.
     *
     * @param bean el bean
     * @return el boolean
     */
    public static boolean fullyValidate(Bean bean) {
        return validate(bean, FullyValidate.class).isEmpty();
    }

    /**
     * Is valid boolean.
     *
     * @param bean el bean
     * @return el boolean
     */
    public static boolean isValid(Bean bean) {
        return isValid(bean, ValidInfo.class);
    }

    /**
     * Validate required boolean.
     *
     * @param bean el bean
     * @return el boolean
     */
    public static boolean validateRequired(Bean bean) {
        return isValid(bean, Required.class);
    }

    /**
     * Validate id candidate boolean.
     *
     * @param bean el bean
     * @return el boolean
     */
    public static boolean validateIdCandidate(Bean bean) {
        return isValid(bean, IdCandidate.class);
    }

    /**
     * Validate natural id candidate boolean.
     *
     * @param bean el bean
     * @return el boolean
     */
    public static boolean validateNaturalIdCandidate(Bean bean) {
        return isValid(bean, NaturalIdCandidate.class);
    }

}

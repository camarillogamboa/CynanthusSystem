package edu.cynanthus.bean;

/**
 * Esta interface de marcado denota un grupo de validación para la especificación
 * BeanVaidation. El uso de este grupo esta sugerido para la validación de campos en
 * clases que representen entidades de datos, marcandolos así como candidatos a algún
 * tipo de identificador natural de la la entidad, dicho de otra forma, el grupo pretende
 * englobar a cualquier campo que sea de naturaleza representativa y única en forma de datos de la vida
 * cotidiana.
 *
 * @author L.G. Camarillo
 */
public interface NaturalIdCandidate extends AnyIdCandidate {
}

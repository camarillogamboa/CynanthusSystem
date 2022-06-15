package edu.cynanthus.auri.api;

import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

/**
 * La interface AuthService abstrae la autenticación de un
 * usuario.
 *
 * Esta interface esta pensada solo para los casos en la que
 * la autenticación es exitosa. Para el caso contrario, el error de autenticación de usuario
 * debe manejarse con el uso de excepciones que puedan ser tratadas por alguna capa de seguridad
 * (que por supuesto debe ser independiente al servicio actual)
 *
 */
public interface AuthService extends AuriService {

    /**
     * La constante AUTH_SERVICE_PATH.
     */
    String AUTH_SERVICE_PATH = AURI_PATH + "/auth";

    /**
     * Realiza la autenticación de un usuario a partir
     * de un objeto User el cual debe contener
     * los campos username y password. El servicio implementador
     * deberá retornar un objeto AuthenticatedUser con la información de inicio de sesión y el token
     * de autenticación apropiado.
     *
     * El error de autenticación debe ser lanzado por medio de alguna excepción derivada de RuntimeException.
     *
     * @param user el objeto usuario que se va a autenticar
     * @return el usuario atenticado
     */
    AuthenticatedUser auth(User user);

}

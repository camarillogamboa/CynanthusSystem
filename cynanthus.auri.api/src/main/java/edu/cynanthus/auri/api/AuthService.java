package edu.cynanthus.auri.api;

import edu.cynanthus.domain.AuthenticatedUser;
import edu.cynanthus.domain.User;

/**
 * La interface AuthService abstrae la autenticación de un
 * usuario.
 */
public interface AuthService extends AuriService {

    /**
     * Realiza la autenticación de un usuario a partir
     * de un objeto User el cual debe contener
     * los campos username y password. El servicio implementador
     * deberá retornar un objeto AuthenticatedUser con el token
     * de autenticación apropiado.
     *
     * @param user el user
     * @return el authenticated user
     */
    AuthenticatedUser auth(User user);

}

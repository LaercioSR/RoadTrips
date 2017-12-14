package br.uefs.ecomp.RoadTrips.model;

/**
 * Enumeração define o tipo do usuário, sendo o <b>admin</b> possui alguns 
 * previlegio que o <b>usuario</b> comum não tem
 * @see Usuario
 */
public enum TipoUsuario {
    /**
     * Administrador do programa
     */
    admin, 
    /**
     * Usuario comum
     */
    usuario;
}

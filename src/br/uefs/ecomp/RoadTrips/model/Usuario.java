package br.uefs.ecomp.RoadTrips.model;

import java.util.LinkedList;

/**
 * Essa classe o comportamento de usuário do programa, que poderá criar e manipular
 * viagens na aplicação
 * @see TipoUsuario
 */
public class Usuario {
    private TipoUsuario tipoUsuario;
    private String login;
    private String senha;
    private LinkedList<Viagem> viagens;
}

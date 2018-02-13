package br.uefs.ecomp.RoadTrips.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Essa classe o comportamento de usuário do programa, que poderá criar e manipular
 * viagens na aplicação
 * @see TipoUsuario
 */
public class Usuario implements Serializable {
    private TipoUsuario tipoUsuario;
    private String login;
    private String senha;
    private String email;
    private LinkedList<Viagem> viagens;

    public Usuario(TipoUsuario tipoUsuario, String login, String senha, String email) {
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.senha = senha;
        this.email = email;
        viagens = new LinkedList();
    }

    /**
     * Método retorna o login do usuário.
     * @return Login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Método retorna a senha do usuário.
     * @return Senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Método retorna o tipo do usuário.
     * @return Tipo do usuário.
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Método retorna o email do usuário.
     * @return Email do usuário.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Método retorna verdadeiro se o usuário é ADMIN.
     * @return True se o usuário é ADMIN.
     */
    public boolean isAdmin() {
        return tipoUsuario == TipoUsuario.admin;
    }

    /**
     * Método modifica o tipo do usuário.
     * @param tipoUsuario Tipo do usuário.
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    /**
     * Método adiciona a viagem ao usuário.
     * @param viagem Viagem a ser adicionada so usuário.
     */
    public void addViagem(Viagem viagem) {
        viagens.add(viagem);
    }
    
    /**
     * Iterador com as viagens do usuário.
     * @return Iterator das vviagens.
     */
    public Iterator iteratorViagem() {
        return viagens.descendingIterator();
    }
    
    /**
     * Método compara se dois usuários são iguais pelo login.
     * @param obj Objeto que será comparada.
     * @return True se os dois usuários são iguais.
     */
    @Override
    public boolean equals(Object obj) {
        return login.equals(((Usuario) obj).getLogin()) && senha.equals(((Usuario) obj).getSenha());
    }
}

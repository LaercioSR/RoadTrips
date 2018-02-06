package br.uefs.ecomp.RoadTrips.model;

import java.util.Iterator;
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
    private String email;
    private LinkedList<Viagem> viagens;

    public Usuario(TipoUsuario tipoUsuario, String login, String senha, String email) {
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.senha = senha;
        this.email = email;
        viagens = new LinkedList();
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean isAdmin() {
        return tipoUsuario == TipoUsuario.admin;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public void addViagem(Viagem viagem) {
        viagens.add(viagem);
    }
    
    public Iterator iteratorViagem() {
        return viagens.descendingIterator();
    }
    
    @Override
    public boolean equals(Object o) {
        return login.equals(((Usuario) o).getLogin()) && senha.equals(((Usuario) o).getSenha());
    }
}

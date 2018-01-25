package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.TipoUsuario;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.util.Grafo;
import br.uefs.ecomp.RoadTrips.util.HashMap;
import br.uefs.ecomp.RoadTrips.util.HashingSenha;

public class RoadTripsController {
    Grafo pontos;
    HashMap usuarios;

    public RoadTripsController() {
        this.pontos = new Grafo();
        this.usuarios = new HashMap();
    }
    
    public boolean usuarioCadastrado(Usuario usuario) {
        try {
            usuarios.get(usuario.getLogin());
        } catch (DadoNaoEncontradoException ex) {
            return false;
        }
        return true;
    }
    
    public void cadastrarUsuario(String login, String senha, String email) throws DadoDuplicadoException {
        Usuario usuario;
        TipoUsuario tipo;
        if(usuarios.isEmpty()){
            tipo = TipoUsuario.admin;
        } else{
            tipo = TipoUsuario.usuario;
        }
        usuario = new Usuario(tipo, login, HashingSenha.condificar(senha), email);
        
        usuarios.put(login, usuario);
    }
    
    public Usuario autenticarLogin(String login, String senha) throws DadoNaoEncontradoException{
        Usuario usuario = (Usuario) usuarios.get(login);
        if(usuario.getSenha().equals(HashingSenha.condificar(senha)))
            return usuario;
        
        throw new DadoNaoEncontradoException();
    }
    
    public void adicionarCidade(String nome, double area, int populacao, String descricao, double latitude, double longitude) throws DadoDuplicadoException {
        Cidade cidade = new Cidade(nome, area, populacao, descricao, latitude, longitude);
        
        pontos.addVertex(cidade);
    }
}
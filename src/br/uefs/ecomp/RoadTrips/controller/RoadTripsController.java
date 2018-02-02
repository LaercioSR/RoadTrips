package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.exceptions.DadoDuplicadoException;
import br.uefs.ecomp.RoadTrips.exceptions.DadoNaoEncontradoException;
import br.uefs.ecomp.RoadTrips.model.Cidade;
import br.uefs.ecomp.RoadTrips.model.Estabelecimento;
import br.uefs.ecomp.RoadTrips.model.Intersecao;
import br.uefs.ecomp.RoadTrips.model.Ponto;
import br.uefs.ecomp.RoadTrips.model.TipoEstabelecimento;
import br.uefs.ecomp.RoadTrips.model.TipoIntersecao;
import br.uefs.ecomp.RoadTrips.model.TipoUsuario;
import br.uefs.ecomp.RoadTrips.model.Usuario;
import br.uefs.ecomp.RoadTrips.util.Grafo;
import br.uefs.ecomp.RoadTrips.util.HashMap;
import br.uefs.ecomp.RoadTrips.util.HashingSenha;
import br.uefs.ecomp.RoadTrips.util.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.image.Image;

public class RoadTripsController {
    Grafo pontos;
    HashMap usuarios;
    int numPontos;

    public RoadTripsController() {
        this.pontos = new Grafo();
        this.usuarios = new HashMap();
        numPontos = 0;
    }
    
    public Iterator iteratorPontos() {
        return pontos.iterator();
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
        usuario = new Usuario(tipo, login, HashingSenha.codificar(senha), email);
        
        usuarios.put(login, usuario);
    }
    
    public Usuario buscarUsuario(String nomeUsuario) throws DadoNaoEncontradoException {
        return (Usuario) usuarios.get(nomeUsuario);
    }
    
    public Usuario autenticarLogin(String login, String senha) throws DadoNaoEncontradoException{
        Usuario usuario = (Usuario) usuarios.get(login);
        if(usuario.getSenha().equals(HashingSenha.codificar(senha)))
            return usuario;
        
        throw new DadoNaoEncontradoException();
    }
    
    public void adicionarCidade(String nome, double area, int populacao, String descricao, double latitude, 
                                double longitude, LinkedList<Image> imagens) throws DadoDuplicadoException {
        
        Cidade cidade = new Cidade(nome, ++numPontos, area, populacao, descricao, latitude, longitude, imagens);
        
        pontos.addVertex(cidade);
    }
    
    public Intersecao adicionarIntersecao(TipoIntersecao tipoIntersecao, String nome, double latitude, double longitude) throws DadoDuplicadoException {
        Intersecao intersecao = new Intersecao(tipoIntersecao, ++numPontos, nome, latitude, longitude);
        
        pontos.addVertex(intersecao);
        
        return intersecao;
    }
    
    public void adicionarRota(Ponto pontoA, Ponto pontoB, double distancia) throws DadoNaoEncontradoException, DadoDuplicadoException{
        pontos.addEdge(pontoA, pontoB, numPontos);
    }
    
    public Vertex buscarVerticePonto(Ponto Ponto) throws DadoNaoEncontradoException {
        return pontos.getVertex(Ponto);
    }
    
    public void adicionarEstabelecimento(String nome, TipoEstabelecimento tipoEstabelecimento, Cidade cidade, LinkedList<Image> imagens) throws DadoDuplicadoException {
        if(tipoEstabelecimento == TipoEstabelecimento.LugaresParaComer){
            cidade.addLugarComer(new Estabelecimento(nome, tipoEstabelecimento, cidade, imagens));
        }
    }
}
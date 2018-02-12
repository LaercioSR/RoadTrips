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
import br.uefs.ecomp.RoadTrips.model.Viagem;
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

    /**
     * Constroi um controller do projeto.
     */
    public RoadTripsController() {
        this.pontos = new Grafo();
        this.usuarios = new HashMap();
        numPontos = 0;
    }
    
    /**
     * Método retorna o iterador dos pontos cadastrados no sistema.
     * @return Iterator de pontos.
     */
    public Iterator iteratorPontos() {
        return pontos.iterator();
    }
    
    /**
     * Método retorna verdadeiro se o login passado já possui um usuário cadastrado.
     * @param loginUsuario Login para verificação.
     * @return True se já existe usuário com o login passado.
     */
    public boolean usuarioCadastrado(String loginUsuario) {
        try {
            usuarios.get(loginUsuario);
        } catch (DadoNaoEncontradoException ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Método cria e cadastra um usuário com os dados passados.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param email Email do usuário.
     * @throws DadoDuplicadoException Caso já exista usuário cadastrado com o login passado.
     */
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
    
    /**
     * Método retorna o usuário que possui o login passado como paramêtro.
     * @param loginUsuario Login para buscar usuário.
     * @return Usuário buscado.
     * @throws DadoNaoEncontradoException Caso não encontre usuário com login passado.
     */
    public Usuario buscarUsuario(String loginUsuario) throws DadoNaoEncontradoException {
        return (Usuario) usuarios.get(loginUsuario);
    }
    
    /**
     * Método autentica o login e retorna o usuário com os dados passado.
     * @param login Login do usuário.
     * @param senha Suposta senha do usuário.
     * @return Usuário que teve seus dados autenticados.
     * @throws DadoNaoEncontradoException Caso a autenticação não seja bem sucedida.
     */
    public Usuario autenticarLogin(String login, String senha) throws DadoNaoEncontradoException{
        Usuario usuario = (Usuario) usuarios.get(login);
        if(usuario.getSenha().equals(HashingSenha.codificar(senha)))
            return usuario;
        
        throw new DadoNaoEncontradoException();
    }
    
    /**
     * Método cria e adiciona uma cidade ao sistema.
     * @param nome Nome da cidade.
     * @param area Area da cidade.
     * @param populacao Populaçao da cidade.
     * @param descricao Descrição da cidade.
     * @param latitude Latitude da cidade.
     * @param longitude Longitude da cidade.
     * @param imagens Lista com imagens da cidade.
     * @throws DadoDuplicadoException Caso já exista ponto com o nome passado.
     */
    public void adicionarCidade(String nome, double area, int populacao, String descricao, double latitude, 
                                double longitude, LinkedList<Image> imagens) throws DadoDuplicadoException {
        
        Cidade cidade = new Cidade(nome, ++numPontos, area, populacao, descricao, latitude, longitude, imagens);
        
        pontos.addVertex(cidade);
    }
    
    /**
     * Método cria e adiciona uma interseção ao sistema.
     * @param tipoIntersecao Tipo da interseção.
     * @param nome Nome da interseção.
     * @param latitude Latitude da interseção.
     * @param longitude Longitude da interseção.
     * @return Interseção ciada.
     * @throws DadoDuplicadoException Caso já exista ponto com o nome passado.
     */
    public Intersecao adicionarIntersecao(TipoIntersecao tipoIntersecao, String nome, double latitude, double longitude) throws DadoDuplicadoException {
        Intersecao intersecao = new Intersecao(tipoIntersecao, ++numPontos, nome, latitude, longitude);
        
        pontos.addVertex(intersecao);
        
        return intersecao;
    }
    
    /**
     * Método cria e adiciona uma rota ao sistema.
     * @param pontoA Ponto A da rota.
     * @param pontoB Ponto B da rota.
     * @param distancia Distância da rota.
     * @throws DadoNaoEncontradoException Caso não encontre um dos pontos no sistema.
     * @throws DadoDuplicadoException Caso já exista uma rota entre os dois pontos.
     */
    public void adicionarRota(Ponto pontoA, Ponto pontoB, double distancia) throws DadoNaoEncontradoException, DadoDuplicadoException{
        pontos.addEdge(pontoA, pontoB, numPontos);
    }
    
    /**
     * Método retorna o vértice ({@link br.uefs.ecomp.RoadTrips.util.Vertex Vertex}) do ponto passado.
     * @param Ponto Ponto que é desejado receber o vértice ({@link br.uefs.ecomp.RoadTrips.util.Vertex Vertex}) equivalente.
     * @return Vertex do ponto.
     * @throws DadoNaoEncontradoException Caso não encontre o vértice equivalente ao ponto.
     */
    public Vertex buscarVerticePonto(Ponto Ponto) throws DadoNaoEncontradoException {
        return pontos.getVertex(Ponto);
    }
    
    /**
     * Método cria e adiciona um estabelecimento ao sistema.
     * @param nome Nome do estabelecimento.
     * @param tipoEstabelecimento Tipo do estabelecimento.
     * @param cidade Cidade do estabelecimento.
     * @param imagens Lista de imagens do estabelecimento.
     * @throws DadoDuplicadoException Caso já exita um estabelecimento com o nome passado.
     */
    public void adicionarEstabelecimento(String nome, TipoEstabelecimento tipoEstabelecimento, Cidade cidade, LinkedList<Image> imagens) throws DadoDuplicadoException {
        if(tipoEstabelecimento == TipoEstabelecimento.LugaresParaComer){
            cidade.addLugarComer(new Estabelecimento(nome, tipoEstabelecimento, cidade, imagens));
        }
    }
    
    /**
     * Método cria uma viagem com o nome passado.
     * @param nomeViagem Nome da viagem.
     * @return Viagem criada.
     */
    public Viagem criarViagem(String nomeViagem) {
        Viagem viagem = new Viagem(nomeViagem);
        
        return viagem;
    }
    
    /**
     * Método adiciona a viagem ao usuário passados como paramêtros.
     * @param usuario Usuário que receberá a viagem.
     * @param viagem Viagem que será adicionada ao usuário.
     */
    public void adiocionarViagemUsuario(Usuario usuario, Viagem viagem) {
        usuario.addViagem(viagem);
    }
}
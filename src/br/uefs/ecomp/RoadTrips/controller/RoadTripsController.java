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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Classe controller principal do programa, que armazena e manipula os dados do Model.
 */
public class RoadTripsController implements Serializable {
    private Grafo pontos;
    private HashMap usuarios;
    private int numPontos;

    /**
     * Constroi um controller do projeto.
     */
    public RoadTripsController() {
        this.pontos = new Grafo();
        this.usuarios = new HashMap();
        numPontos = 0;
    }

    /**
     * Método retorna a lista de usuário cadastrados.
     * @return Lista de usuário cadastrados.
     */
    public HashMap getUsuarios() {
        return usuarios;
    }

    /**
     * Método carrega os usuário de uma lista para o HashMap de usuários.
     * @param listaUsuarios 
     */
    public void setUsuarios(HashMap listaUsuarios) {
        Iterator it = listaUsuarios.iterator();
        
        while(it.hasNext()){
            Usuario usuario = (Usuario) it.next();
            try {
                usuarios.put(usuario.getLogin(), usuario);
            } catch (DadoDuplicadoException ex) { }
        }
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
     * Método retorna verdadeiro se o ponto passada já está cadastrado (Pode ser usado 
     * tanto para cidade como para interseção).
     * @param ponto Nome para verificação.
     * @return True se essa cidade já existe.
     */
    public boolean pontoCadastrado(Ponto ponto) {
        try {
            pontos.getVertex(ponto);
        } catch (DadoNaoEncontradoException ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Método busca uma cidade pelo nome.
     * @param nomeCidade Nome da cidade.
     * @return Cidade Buscada.
     * @throws DadoNaoEncontradoException Caso a cidade não seja encontrada.
     */
    public Cidade buscarCidade(String nomeCidade) throws DadoNaoEncontradoException {
        return (Cidade) pontos.getVertex(new Cidade(nomeCidade)).getData();
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
     * @return Cidade cadastrada.
     * @throws DadoDuplicadoException Caso já exista ponto com o nome passado.
     */
    public Cidade adicionarCidade(String nome, double area, int populacao, String descricao, double latitude, 
                                double longitude, LinkedList<Image> imagens) throws DadoDuplicadoException {
        
        Cidade cidade = new Cidade(nome, ++numPontos, area, populacao, descricao, latitude, longitude, imagens);
        
        pontos.addVertex(cidade);
        
        return cidade;
    }
    
    /**
     * Método possíbilita ao usuário abrir e carregar um arquivos com cidades.
     * @throws FileNotFoundException Caso o arquivo não seja encontrado.
     * @throws IOException Caso o arquivo não abra corretamente.
     */
    public void lerArquivoCidades() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Arquivo de Texto","*.txt"));
        fileChooser.setTitle("Selecionar Arquivo das Cidades");
        File file = fileChooser.showOpenDialog(null);
        
        BufferedReader buffRead = new BufferedReader(new FileReader(file));
        
        String linha = "";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                StringTokenizer aux = new StringTokenizer(linha);
                String nomeCidade = aux.nextToken();
                double latitudeCidade = Double.parseDouble(aux.nextToken());
                double longitudeCidade = Double.parseDouble(aux.nextToken());
                Cidade cidade = new Cidade(nomeCidade, ++numPontos, latitudeCidade, longitudeCidade);
                try {
                    pontos.addVertex(cidade);
                } catch (DadoDuplicadoException ex) { }
            } else
                break;
        }
        buffRead.close();
    }
    
    /**
     * Método possíbilita ao usuário abrir e carregar um arquivos com rotas.
     * @throws FileNotFoundException Caso o arquivo não seja encontrado.
     * @throws IOException Caso o arquivo não abra corretamente.
     */
    public void lerArquivosRotas() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Arquivo de Texto","*.txt"));
        fileChooser.setTitle("Selecionar Arquivo das Rotas");
        File file = fileChooser.showOpenDialog(null);
        
        BufferedReader buffRead = new BufferedReader(new FileReader(file));
        
        String linha = "";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                StringTokenizer aux = new StringTokenizer(linha);
                try {
                    Cidade pontoA = buscarCidade(aux.nextToken());
                    Cidade pontoB = buscarCidade(aux.nextToken());
                    double distancia = Double.parseDouble(aux.nextToken());
                    adicionarRota(pontoA, pontoB, distancia);
                } catch (DadoNaoEncontradoException | DadoDuplicadoException ex) { }
            } else
                break;
        }
        buffRead.close();
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
        pontos.addEdge(pontoA, pontoB, distancia);
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
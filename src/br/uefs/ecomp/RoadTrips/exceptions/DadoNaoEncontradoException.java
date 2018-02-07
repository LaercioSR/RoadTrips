package br.uefs.ecomp.RoadTrips.exceptions;

import java.io.Serializable;

/**
 * Exceção lançada quando uma item não foi encontrado na estrutura de dado.
 */
public class DadoNaoEncontradoException extends Exception implements Serializable {

    /**
     * Controi a exceção.
     */
    public DadoNaoEncontradoException() {
        super();
    }

    /**
     * Constroi a exceção com uma mensagem que poderá ser exibida.
     * @param message Mensagem que será exibida na tela.
     */
    public DadoNaoEncontradoException(String message) {
        super(message);
    }
}

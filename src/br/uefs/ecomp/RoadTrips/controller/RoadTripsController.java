package br.uefs.ecomp.RoadTrips.controller;

import br.uefs.ecomp.RoadTrips.util.Grafo;
import br.uefs.ecomp.RoadTrips.util.HashMap;

public class RoadTripsController {
    Grafo pontos;
    HashMap usuarios;

    public RoadTripsController() {
        this.pontos = new Grafo();
        this.usuarios = new HashMap();
    }
}
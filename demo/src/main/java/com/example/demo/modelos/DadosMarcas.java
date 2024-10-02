package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas (List<DadosVeiculos> modelos){
}

package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public record DadosVeiculos(String codigo,
                            String nome) {
}

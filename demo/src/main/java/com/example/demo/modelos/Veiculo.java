package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Valor")String valor,
                      @JsonAlias("Marca")String morca,
                      @JsonAlias("Modelo")String modelo,
                      @JsonAlias("AnoModelo")Integer ano,
                      @JsonAlias("Combustivel")String combustivel)
{
}

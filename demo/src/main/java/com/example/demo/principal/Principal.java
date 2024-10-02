package com.example.demo.principal;

import com.example.demo.modelos.DadosMarcas;
import com.example.demo.modelos.DadosVeiculos;
import com.example.demo.modelos.Veiculo;
import com.example.demo.service.ConsumirApi;
import com.example.demo.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final String URL_ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final  String MARCAS = "/marcas";
    private Scanner read = new Scanner(System.in);

    private ConsumirApi consumirApi = new ConsumirApi();

    private ConverteDados converteDados = new ConverteDados();



    public void exibirMenu (){
        String opcoes = """
                ********** OPÇÕES **************
                carros
                motos
                caminhoes
                
                Digite uma das opções para consultar valores:
                """;
        System.out.println(opcoes);
        String gets = read.nextLine();

        String Endereco ="";

        if (gets.toLowerCase().contains("carr")){
            Endereco =URL_ENDERECO+"carros/marcas";

        }else if(gets.toLowerCase().contains("mot")){
            Endereco =URL_ENDERECO+"motos/marcas";


        }else if (gets.toLowerCase().contains("cami")){
            Endereco =URL_ENDERECO+"caminhoes/marcas";

        }


            var json = consumirApi.obterDados(Endereco);
        System.out.println(json);

            var marcas = converteDados.obterLista(json, DadosVeiculos.class);

            marcas.stream().sorted(Comparator.comparing(DadosVeiculos::codigo))
                    .forEach(System.out::println);

            System.out.println("enforme o codigo da marca para consulta");
            var codigoMarca = read.nextLine();

           Endereco = Endereco + "/" + codigoMarca + "/modelos";

           json =consumirApi.obterDados(Endereco);

           var modeloList = converteDados.obterDados(json, DadosMarcas.class);

           System.out.println("\n Modelos dessa marca");

           modeloList.modelos().stream().sorted(Comparator.comparing(DadosVeiculos::codigo))
            .forEach(System.out::println);

           System.out.println("digite o nome do modelo");

           var nomeModelos = read.nextLine();

           List<DadosVeiculos>modelisFiltrados = modeloList.modelos().stream()
                   .filter(m-> m.nome().toLowerCase().contains(nomeModelos.toLowerCase()))
                   .collect(Collectors.toList());

           System.out.println("\n Modelis Filtrados");
            modelisFiltrados.forEach(System.out::println);

            System.out.println("Digite Por vafor o codigo do modelo do veiculo");
            var codigoModelo= read.nextLine();

            Endereco = Endereco+ "/"+ codigoModelo +"/anos";
           json= consumirApi.obterDados(Endereco);
           List<DadosVeiculos> anos =converteDados.obterLista(json, DadosVeiculos.class);

           List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size() ; i++) {
            var EnderecoAnos = Endereco +"/"+ anos.get(i).codigo();
            json= consumirApi.obterDados(EnderecoAnos);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("\n Todos os valores deste modelos por ano");
        veiculos.forEach(System.out::println);




    }
}

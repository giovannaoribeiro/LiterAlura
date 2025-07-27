package org.example;

import Services.ConsumoAPI;
import Services.ConversorDados;
import dados.*;
import repositories.RepositoryAutor;
import repositories.RepositoryLivro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private Scanner scanner = new Scanner(System.in);
    private final static String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDados conversorDados = new ConversorDados();
    private RepositoryLivro repositoryLivro;
    private RepositoryAutor repositoryAutor;
    private List<Autor> autores;
    private List<Livro> livros;

    public Main(RepositoryLivro repositoryLivro, RepositoryAutor repositoryAutor) {
        this.repositoryLivro = repositoryLivro;
        this.repositoryAutor = repositoryAutor;
    }

    public void interacao() {
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("----------------------/n");
            var menu = """
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    0- Sair
                    
                    Escolha o número de sua preferência:
                    """;

            System.out.println(menu);

            while (!scanner.hasNextInt()) {
                System.out.println("Escolha um número disponível no menu.");
                scanner.nextLine();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivro();
                    break;
                case 3:
                    listarAutor();
                    break;
                case 4:
                    listarAutorVivo();
                    break;
                case 5:
                    listarLivroIdioma();
                    break;
                case 0:
                    System.out.println("Volte sempre!");
                    break;
            }
        }
    }

    private DadosBusca getBusca() {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = scanner.nextLine();
        var json = consumoAPI.obterDados(URL_BASE + nomeLivro.replace("", "%20"));
        DadosBusca dados = conversorDados.obterDados(json, DadosBusca.class);
        return dados;
    }

    private void buscarLivro() {
        DadosBusca dadosBusca = getBusca();
        if (dadosBusca != null && !dadosBusca.results().isEmpty()) {
            DadosLivros primeiroLivro = dadosBusca.results().get(0);

            Livro livro = new Livro(primeiroLivro);
            System.out.println("--------Livro-------");
            System.out.println(livro);
            System.out.println("--------------------");

            Optional<Livro> livroExiste = repositoryLivro.findByTitulo(livro.getTitulo());

            if (livroExiste.isPresent()) {
                System.out.println("Livro encontrado.");
            } else {
                if (primeiroLivro.autor().isEmpty()) {
                    DadosAutor autor = primeiroLivro.autor().get(0);
                    Autor autor1 = new Autor(autor);
                    Optional<Autor> autorOptional = repositoryAutor.findByNome(autor1.getNome());

                    if (autorOptional.isPresent()) {
                        Autor autorExiste = autorOptional.get();
                        livro.setAutor(autorExiste);
                        repositoryLivro.save(livro);
                    } else {
                        Autor autorNovo = repositoryAutor.save(autor1);
                        livro.setAutor(autorNovo);
                        repositoryLivro.save(livro);
                    }

                    System.out.println("----------Livro---------");
                    System.out.printf("Título: %s%nAutor: %s%nIdioma: %s%n",
                            livro.getTitulo(), autor1.getNome(), livro.getLinguagem());
                    System.out.println("---------------------");
                } else {
                    System.out.println("Nenhum autor encontrado.");
                }
            }
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private void listarLivro() {
        livros = repositoryLivro.findAll();
        livros.stream().forEach(System.out::println);
    }

    private void listarAutor() {
        autores = repositoryAutor.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarAutorVivo() {
        System.out.println("Digite o ano que deseja listar autores: ");
        var ano = scanner.nextInt();
        autores = repositoryAutor.listarAutorVivo(ano);
        autores.stream().forEach(System.out::println);
    }

    private List<Livro> dadosLivroIdioma(String idioma) {
        var data = Idioma.fromString(idioma);
        System.out.println("Livro Idioma: " + data);

        List<Livro> livroIdioma = repositoryLivro.findByLinguagem(data);
        return livroIdioma;
    }

    private void listarLivroIdioma() {
        System.out.println("Selecione o idioma que deseja: ");

        var opcao = -1;
        while (opcao != 0) {
            var opcoes = """
                    1- en - Inglês
                    2- es - Espanhol
                    3- fr - Francês
                    4- pt - Português
                    
                    0- Retornar às opções anteriores
                    """;

            System.out.println(opcoes);

            while (!scanner.hasNextInt()) {
                System.out.println("Selecione número disponível no menu.");
                scanner.nextLine();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    List<Livro>  livrosIngles = dadosLivroIdioma("[en]");
                    livrosIngles.forEach(System.out::println);
                    break;
                case 2:
                    List<Livro>  livrosEspanhol = dadosLivroIdioma("[es]");
                    livrosEspanhol.forEach(System.out::println);
                    break;
                case 3:
                    List<Livro>  livrosFrances = dadosLivroIdioma("[fr]");
                    livrosFrances.forEach(System.out::println);
                    break;
                case 4:
                    List<Livro>  livrosPortugues = dadosLivroIdioma("[pt]");
                    livrosPortugues.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nenhum idioma selecionado.");
            }
        }
    }
}
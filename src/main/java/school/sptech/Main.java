package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider();
        JdbcTemplate connection = dbConnectionProvider.getConnection();

        connection.execute("""
                CREATE TABLE filme (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    ano INT NOT NULL,
                    genero VARCHAR(255) NOT NULL,
                    diretor VARCHAR(255) NOT NULL
                )
                """);

        // Inserindo alguns filmes

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "Matrix", 1999, "Ficção Científica", "Lana Wachowski, Lilly Wachowski");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "O Poderoso Chefão", 1972, "Drama", "Francis Ford Coppola");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "O Senhor dos Anéis: O Retorno do Rei", 2003, "Fantasia", "Peter Jackson");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "Forrest Gump", 1994, "Drama", "Robert Zemeckis");

        // Listando todos os filmes

        List<Filme> filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        System.out.println("Filmes no banco de dados:");

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Inserindo um novo filme a partir de um objeto

        Filme novoFilme = new Filme(null, "Vingadores: Ultimato", 2019, "Ação", "Anthony Russo, Joe Russo");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                novoFilme.getNome(), novoFilme.getAno(), novoFilme.getGenero(), novoFilme.getDiretor());

        System.out.println("\nFilmes no banco de dados após inserção de novo filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Atualizando um filme

        connection.update("UPDATE filme SET nome = ?, ano = ?, genero = ?, diretor = ? WHERE id = ?",
                "Shrek", 2001, "Animação", "Andrew Adamson, Vicky Jenson", 5);

        System.out.println("\nFilmes no banco de dados após atualização de filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Deletando um filme

        connection.update("DELETE FROM filme WHERE id = ?", 5);

        System.out.println("\nFilmes no banco de dados após exclusão de filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Busca personalizada

        System.out.println("\nFilmes de drama no banco de dados:");

        filmesDoBanco = connection.query("SELECT * FROM filme WHERE genero = ?", new BeanPropertyRowMapper<>(Filme.class), "Drama");

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Buscar um filme pelo ID

        Filme filmeEncontrado = connection.queryForObject("SELECT * FROM filme WHERE id = ?", new BeanPropertyRowMapper<>(Filme.class), 1);
        System.out.println("\nFilme com ID 1: " + filmeEncontrado);

        // Obs: se sua query retornar nenhum ou mais de um item, ao executar, uma exceção será lançada.
    }
}
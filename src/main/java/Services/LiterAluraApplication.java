package Services;

import org.example.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import repositories.RepositoryAutor;
import repositories.RepositoryLivro;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repositories")
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private RepositoryLivro repositoryLivro;
    @Autowired
    private RepositoryAutor repositoryAutor;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(repositoryLivro, repositoryAutor);
        main.interacao();
    }

}

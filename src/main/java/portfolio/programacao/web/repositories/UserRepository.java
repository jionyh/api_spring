package portfolio.programacao.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import portfolio.programacao.web.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
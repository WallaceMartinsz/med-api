package br.com.wallace.app.repository;

import br.com.wallace.app.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    UserDetails findByLogin(String login);

}

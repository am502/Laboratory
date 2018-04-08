package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    User findByEmail(String email);

    User findByNicknameIgnoreCase(String name);

    User findByEmailIgnoreCase(String email);
}

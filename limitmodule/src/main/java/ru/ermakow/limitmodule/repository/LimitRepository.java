package ru.ermakow.limitmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ermakow.limitmodule.model.LimitEntity;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    Optional<LimitEntity> findByClientId(Long clientId);
}

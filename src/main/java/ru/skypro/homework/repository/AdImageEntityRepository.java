package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdImageEntity;

import java.util.Optional;

public interface AdImageEntityRepository extends JpaRepository<AdImageEntity,Long> {
    Optional<AdImageEntity> findByAdEntityId(Long adId);
}

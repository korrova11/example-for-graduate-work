package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdsImageEntity;

import java.util.Optional;

public interface AdsImageEntityRepository extends JpaRepository<AdsImageEntity,Long> {
    Optional<AdsImageEntity> findByAdsEntityId(Long adsId);
}

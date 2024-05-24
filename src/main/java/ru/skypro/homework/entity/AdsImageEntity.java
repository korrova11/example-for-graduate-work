package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photo")
public class AdsImageEntity extends ImageEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adsEntity_id")
    private AdsEntity adsEntity;

}

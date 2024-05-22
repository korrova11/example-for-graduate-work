package ru.skypro.homework.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AdsImageEntity extends ImageEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AdsEntity_id")
    private AdsEntity adsEntity;
}

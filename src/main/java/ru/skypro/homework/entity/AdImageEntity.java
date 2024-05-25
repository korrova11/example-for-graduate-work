package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AdImageEntity extends ImageEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adEntity_id")
    private AdEntity adEntity;

}

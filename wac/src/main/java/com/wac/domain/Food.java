package com.wac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "FOODS")
@SequenceGenerator(name = "FOODS_SEQ_GEN", sequenceName = "FOODS_SEQ", initialValue = 1, allocationSize = 1)
public class Food extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOODS_SEQ_GEN")
    @Column(name = "food_id")
    private Integer foodId;
    
    @Column(name = "food_name")
    private String foodName;
    
    @Column
    private String kind; // 버거: 1, 사이드: 2, 음료: 3, 세트: 4, 맥모닝: 5, 맥모닝 세트: 6, 맥런치 세트: 7
    
    @Column
    private String price;
    
    @Column
    private String content;
    
    @Column
    private String image;
    
    @Column
    private String selltf; // 판매 중: t , 판매중단: f
}

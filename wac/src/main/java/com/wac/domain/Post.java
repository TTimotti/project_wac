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
@Entity(name = "POSTS")
@SequenceGenerator(name = "POSTS_SEQ_GEN", sequenceName = "POSTS_SEQ", initialValue = 1, allocationSize = 1)
public class Post extends BaseTimeEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ_GEN")
    @Column(name = "post_id")
    private Integer postId;
    
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;
    
    public  Post update(String title, String content) {
        this.title = title;
        this.content = content;
        
        return this;
    }
}












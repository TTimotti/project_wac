/**
 * @author 추지훈
 */
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
    
    @Column(length = 100000000)
    private String content;
    
    @Column
    private String author; // 근데 이거 사실 회원은 주문만가능하고 추가는 관리자만해서 필요가 없긴합니다. 일단 둘게요
    
    @Column
    private Integer kind; // 1: 프로모션, 2: 새로운소식(공지사항), 3: 스토리, 4: 기타.
    
    @Column
    private Integer image;
    
    public  Post update(String title, String content) {
        this.title = title;
        this.content = content;
        
        return this;
    }
}












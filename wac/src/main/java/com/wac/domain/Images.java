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


/**
 * 이미지 저장을 위한 엔터티 클래스
 * 1. 유저 경로에 폴더 생성
 * 2. 오리지널 파일명을 이름과 확장자로 나눠서 폴더 안에 파일로 저장 !!!{파일(file)과 이미지 파일(img)은 다르다}!!!
 *      ex) image.jpg --> Name = image(파일), Extension = .jpg(파일을 이미지(jpeg, png 등)로 변환해주는 확장자)
 * 3. 경로를 DB에 저장 후 불러올 때는 이름과 확장자를 합쳐서 불러옴
 * @author 김지훈
 *
 */
@Entity(name = "IMAGES")
@SequenceGenerator(name = "IMAGES_SEQ_GEN", sequenceName = "IMAGES_SEQ", initialValue = 1, allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Images {

    @Id
    @GeneratedValue(generator = "IMAGES_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    private Integer fid;
    
    @Column(nullable = false)
    private String fileName;
    
    @Column(nullable = false)
    private String originalName;
    
    @Column(nullable = false)
    private String fileUrl;
    
    @Column(nullable = false)
    private String extension;

    public Images update(String fileName, String originalName, String fileUrl, String extension) {
        this.fileName = fileName;
        this.originalName = originalName;
        this.fileUrl = fileUrl;
        this.extension = extension;
        
        return this;
        
    }
}

package com.wac.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wac.domain.Images;
import com.wac.domain.Menu;
import com.wac.repository.ImagesRepository;
import com.wac.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImagesService {

    private final ImagesRepository imagesRepository;
    private final MenuRepository menuRepository;
    
    @Transactional(readOnly = true) // 검색 속도가 빨라짐.
    public List<Images> read() {
        log.info("read() 호출");

        return imagesRepository.findByOrderByFidDesc();
    }
    
    @Value("${file.dir}")
    private String fileDir;
    
    /**
     * fid로 이미지 찾는 서비스
     * 
     * @param Integer fid
     * @return Images
     * @author 김지훈
     */
    public Images readByFid(Integer fid) {
        log.info("readByFid(fid={}) 호출", fid);
        
        return imagesRepository.findByFid(fid);
    }

    
    /**
     * 메뉴 이미지 저장 메서드
     * 경로는 TEAMS_FID를 이용해서 IMAGES와 연동
     * C:\Users\sbs\이미지파일.확장자
     * @param file
     * @return fid 값(Integer)
     * @throws IllegalStateException 
     * @throws IOException
     * 김지훈
     */
    public Integer saveImage(MultipartFile file) throws IllegalStateException, IOException {
        log.info("saveImage(file={}) 호출", file);
        if (file.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String origName = file.getOriginalFilename();

        // 저장할 이름 생성
        String fileName = "Menu" + origName.substring(0, origName.indexOf("."));

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + fileName + extension;

        // 파일 엔티티 생성
        Images image = Images.builder().originalName(origName).fileName(fileName).fileUrl(savedPath)
                .extension(extension).build();

        File makeFolder = new File("D:\\wac");
        
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!makeFolder.exists()) {
            makeFolder.mkdir(); //폴더 생성합니다.
            log.info("폴더 생성");
        }

        // 실제로 로컬에 저장
        file.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장 (레퍼지토리 호출)
        Images savedFile = imagesRepository.save(image);

        return savedFile.getFid();
        
    }

    
    /**
     * 메뉴 이미지 업데이트 메서드
     * @param file
     * @param menuId
     * @author 서범수.
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @Transactional
    public Integer updateMenuImage(MultipartFile file, Integer menuId) throws IllegalStateException, IOException {
        log.info("updateMenuImage(file={}, teamId={}) 호출", file, menuId);
        if (file.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String origName = file.getOriginalFilename();

        // 저장할 이름 생성
        String fileName = "Menu" +origName.substring(0, origName.indexOf("."));

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + fileName + extension;

        // 실제로 로컬에 저장
        file.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장 (레퍼지토리 호출)
        Menu menu = menuRepository.findByMenuId(menuId).get();
        Integer fid = menu.getImage();
        
        Images entity = imagesRepository.findByFid(fid);
        log.info("updateMenuImage.entity = {} " , entity);
        entity.update(fileName, origName, savedPath, extension);

        return fid;
        
    }
}

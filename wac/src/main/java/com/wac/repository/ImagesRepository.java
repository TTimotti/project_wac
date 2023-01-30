package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Images;

public interface ImagesRepository extends JpaRepository<Images, Integer>  {

    List<Images> findByOrderByFidDesc();

    Images findByFid(Integer fid);
}

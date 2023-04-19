package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.*;
import java.util.UUID;

@Log4j2
@RestController
public class ReviewFileController {
    @Autowired
    private ReviewService reviewService;
//    private final static String imageDirectory= Paths.get("").toAbsolutePath()+"/images/";
    private final static String imageDirectory= "/Users/han-yechan/Desktop/파이널/vue/Vegan-Vue.js/src/assets/img/reviewimg";

    @PostMapping(value = "/reviewInsertFiles")
    public ResponseEntity uploadFiles(@RequestParam("file") MultipartFile file, @RequestPart(required = false) ReviewDTO reviewDTO) {
        System.out.println(imageDirectory);
//        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            String filePath = imageDirectory + File.separator + fileName;

            System.out.println("=======================");
            System.out.println(reviewDTO);
            System.out.println(filePath);
            System.out.println(UUID.randomUUID());
            System.out.println(file.getOriginalFilename());


            reviewDTO.setImages(fileName);

            reviewService.reviewCreate(reviewDTO);

            try (FileOutputStream writer = new FileOutputStream(filePath)) {
                writer.write(file.getBytes());
                return new ResponseEntity(HttpStatus.CREATED);
            } catch (Exception e) {
                log.error(e.getMessage(), e);

                throw new RuntimeException("Fail to upload files.");
//            }
        }
    }
}

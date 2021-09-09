package com.example.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Dictionary;

/**
 * 文件上传测试
 */
@Slf4j
@Controller
public class FormController {

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }


    /**
     * 表单里面普通参数获取 @RequestParam
     * 表单里面的文件获取  @RequestPart
     * MultipartFile 自动封装上传过来的文件
     * 从请求参数中获取以下
     * @RequestParam("email") String email,
     * @RequestParam("username") String username,
     * @RequestPart("headerImg")MultipartFile headerImg,
     * @RequestPart("photos")MultipartFile photos
     *
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg")MultipartFile headerImg,
                         @RequestPart("photos")MultipartFile[] photos) throws IOException {

      //日志检查上传的值对不对,保证基本信息获取到，下一步是如何将这些信息存储在服务器
        log.info("上传信息：email={},username={},headerImg={},photos={}",
                email,username,headerImg.getSize(),photos.length);//photos.length:上传文件个数


        //如何将这些信息存储在本地磁盘
        if(!headerImg.isEmpty()){
            String originalFilename = headerImg.getOriginalFilename();//拿到文件名
            headerImg.transferTo(new File("/Users/yyq/Desktop/doooo/"+originalFilename));
        }

        if(photos.length > 0){
            for (MultipartFile photo:photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("/Users/yyq/Desktop/doooo/"+originalFilename));
                }
            }
        }
        return "main";
    }
}

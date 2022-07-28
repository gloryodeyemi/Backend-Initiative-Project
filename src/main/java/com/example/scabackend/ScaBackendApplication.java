package com.example.scabackend;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ScaBackendApplication {

    @Bean
    public Cloudinary cloudinaryConfig() {
//        Cloudinary cloudinary = null;
//        Map config = new HashMap();
//        config.put("cloud_name", "glowcodes");
//        config.put("api_key", "842777667256463");
//        config.put("api_secret", "_0H3PyGpJUq3o3qh0mgPWW-ZEmw");
//        cloudinary = new Cloudinary(config);
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "glowcodes",
        "api_key", "842777667256463",
        "api_secret", "_0H3PyGpJUq3o3qh0mgPWW-ZEmw"
        ));
        return cloudinary;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScaBackendApplication.class, args);

//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "glowcodes",
//        "api_key", "842777667256463",
//        "api_secret", "_0H3PyGpJUq3o3qh0mgPWW-ZEmw"
//        ));
//
//        File uploadedFile = new File("Animhorse.gif");
//        Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
    }

}

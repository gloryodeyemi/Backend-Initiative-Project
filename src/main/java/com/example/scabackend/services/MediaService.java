package com.example.scabackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.scabackend.models.Media;
import com.example.scabackend.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MediaService {
    @Autowired
    Cloudinary cloudinary;

    @Autowired
    MediaRepository mediaRepository;

    public String uploadMedia(MultipartFile profileImg) throws IOException {
        try {
            File uploadedFile = convertMultiPartToFile(profileImg);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public LinkedHashMap<String, Object> modifyJsonResponse(String requestType, String imageUrl){
        LinkedHashMap<String, Object> jsonResponse = new LinkedHashMap<>();
        Media pictures = mediaRepository.findMediaByImageUrl(imageUrl);
        if(requestType.equals("create")){
            jsonResponse.put("status", "success");
            LinkedHashMap<String, String > data = new LinkedHashMap<>();
            data.put("id", pictures.getId().toString());
            data.put("message","Image successfully posted");
            data.put("createdOn", pictures.getCreatedOn().toString());
            data.put("title", pictures.getTitle());
            data.put("imageUrl", imageUrl);

            jsonResponse.put("data", data);
        }

        if(requestType.equals("delete")){
            jsonResponse.put("status", "success");
            LinkedHashMap<String, String > data = new LinkedHashMap<>();
            data.put("message","Image post successfully deleted");
            jsonResponse.put("data", data);
        }

        if(requestType.equals("get")){

            jsonResponse.put("status", "success");
            LinkedHashMap<String, Object > data = new LinkedHashMap<>();

            data.put("id", pictures.getId().toString());
            data.put("createdOn", pictures.getCreatedOn().toString());
            data.put("title", pictures.getTitle());
            data.put("url", pictures.getImageUrl());
        }
        return jsonResponse;
    }
}

package com.oasm.utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {


    @Transactional
    public String fileUpload (MultipartFile file) {

        String path = new File("C:\\Users\\jc121\\Desktop\\file").getAbsolutePath();
        UUID uuid = UUID.randomUUID();
        String oriName = file.getOriginalFilename();

        String newName = uuid.toString() + (oriName != null ? oriName.substring(oriName.lastIndexOf("."))  : null);

        try {
            file.transferTo(new File(path, newName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newName;
    }

}

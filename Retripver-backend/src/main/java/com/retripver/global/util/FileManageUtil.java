package com.retripver.global.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FileManageUtil {
	
    public String generateFileName(String originName) {
    	String randomName = UUID.randomUUID().toString().replaceAll("-", "");
    	String extension = originName.substring(originName.lastIndexOf('.'));
    	
        return randomName + extension;
    }
}

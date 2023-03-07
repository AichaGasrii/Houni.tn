package com.esprit.achat.rest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileImageUploadController {
	@PostMapping("/upload-file")
	public String uploadImage(@RequestParam("file")MultipartFile file) throws Exception{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		String Path_Directory="C:\\Centrale-d-achat-P-I4-me-int-gr-avecmetier-old\\src\\main\\resources\\static\\downloadFile";
		Files.copy(file.getInputStream(),Paths.get(Path_Directory+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		return "Successfuly Image is Upload";
	}

}

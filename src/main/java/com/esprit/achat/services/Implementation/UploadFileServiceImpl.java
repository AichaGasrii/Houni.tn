package com.esprit.achat.services.Implementation;

import java.io.File;
import java.io.FileOutputStream;
import com.esprit.achat.rest.OffreServiceController;
import com.esprit.achat.rest.OffreProduitController;
import com.esprit.achat.services.Interface.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class UploadFileServiceImpl implements UploadFileService {
	@Value("${file.upload}")
	private String pathFile;
	
	private static Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	

	@Override
	public boolean addFile(MultipartFile file) {
		try {
			File convertFile = new File(pathFile + file.getOriginalFilename());

		
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
		} catch (Exception e) {
			
			log.error("upload file :",e.getMessage());

			return false;

		}
		return true;

	}
}

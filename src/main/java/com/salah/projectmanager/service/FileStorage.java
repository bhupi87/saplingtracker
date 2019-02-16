package com.salah.projectmanager.service;	
	
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
	 
/**
 * Created by bhupendra.
 */

public interface FileStorage {
		public void store(MultipartFile file);
		public Resource loadFile(String filename);
		public void deleteAll();
		public void init();
		public Stream<Path> loadFiles();
}



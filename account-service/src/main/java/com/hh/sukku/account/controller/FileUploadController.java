package com.hh.sukku.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hh.sukku.account.service.FileUploadService;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 10:23:15 pm
 */

@RestController
@RequestMapping(value = "v1/file-upload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping(value = "/store")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, 
			@RequestParam("docTypeId") int docTypeid,
			@RequestParam("oldDocId") String oldDocId) throws Exception {
		
		return new ResponseEntity<String>(fileUploadService.store(file, docTypeid, oldDocId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/view")
	public ResponseEntity<Resource> viewFile(@RequestParam("docTypeId") int docTypeId,
			@RequestParam("docId") String docId) throws Exception {
		
		return new ResponseEntity<Resource>(fileUploadService.loadFile(docId, docTypeId), HttpStatus.OK);
	}
}

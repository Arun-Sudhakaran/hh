package com.hh.sukku.account.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hh.sukku.common.exception.CommonException;
import com.hh.sukku.common.params.ParamsFileOperations;
import com.hh.sukku.common.util.ErrorCodes;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 10:23:08 pm
 */

@Service
public class FileUploadService {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadService.class);
	
	private static final List<String> SUPPORTED_DOCS = new ArrayList<String>(Arrays.asList(MediaType.IMAGE_JPEG_VALUE,
			MediaType.APPLICATION_PDF_VALUE, MediaType.IMAGE_PNG_VALUE, "application/msword"));

	private static final int USER_PIC = 1;
	private static final int PRODUCT_PIC = 2;
	
	private static final int MIN_FILE_SIZE_KB = 10;
	private static final int MAX_FILE_SIZE_KB = 90;

	@Autowired
	private ParamsFileOperations paramsFileOperations;

	public String store(MultipartFile file, int docTypeid, String oldDocId) throws Exception {

		checkValidDocument(file);
		
		String newFileName = null;

		try {

			String serverPath = getPath(docTypeid);

			newFileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

			Path rootLocation = Paths.get(serverPath);

			Files.copy(file.getInputStream(), rootLocation.resolve(newFileName));

			if (LOG.isInfoEnabled())
				LOG.info("File Stored with id : {} in {} ", newFileName, rootLocation);

			Files.delete(Paths.get(serverPath + oldDocId));

			if (LOG.isInfoEnabled())
				LOG.info("Old file deleted");

		} catch (Exception e) {
			LOG.error("Exception while upload : {}", e.getMessage());
		}

		return newFileName;
	}

	public Resource loadFile(String docId, int docTypeid) {

		try {

			Path rootLocation = Paths.get(getPath(docTypeid));

			Path file = rootLocation.resolve(docId);

			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			}

			return null;

		} catch (MalformedURLException e) {

			LOG.error("MalformedURLException Exception loadFile");
			throw new CommonException("Document not found!");
		} catch (Exception e) {

			LOG.error("Error occured : ", e.getMessage());
			throw new CommonException("Document not found!");
		}
	}
	
	private void checkValidDocument(MultipartFile file) {
		if (!SUPPORTED_DOCS.contains(file.getContentType())) {
			LOG.error("Invalid Document Format. Content Type : {}", file.getContentType());
			throw new CommonException("Invalid Document Format", ErrorCodes.INVALID_DOCUMENT);
		}

		long fileSizeInKB = file.getSize()/1024;
		if (fileSizeInKB > MAX_FILE_SIZE_KB || fileSizeInKB < MIN_FILE_SIZE_KB) {
			LOG.error("Document size should be greater than {}KB or lesser than {}KB. Content Type : {}", MIN_FILE_SIZE_KB, MAX_FILE_SIZE_KB, file.getContentType());
			throw new CommonException("Document size should be greater than " + MIN_FILE_SIZE_KB + "KB or lesser than " + MAX_FILE_SIZE_KB + 
					"KB. Content Type : " + file.getContentType(), ErrorCodes.INVALID_DOCUMENT_NAME);
		}

		if (file.getOriginalFilename().contains("..")) {
			LOG.error("Document name contain .. : {}", file.getOriginalFilename());
			throw new CommonException("Sorry! Filename contains invalid path sequence " + file.getOriginalFilename(), ErrorCodes.INVALID_DOCUMENT);
		}

	}

	private String getPath(int docTypeid) {

		String path= null;

		switch(docTypeid) {

		case USER_PIC :
			path = paramsFileOperations.getFileUploadPathUser();
			break;

		case PRODUCT_PIC :
			path = paramsFileOperations.getFileUploadPathProduct();
			break;

		default:

		}

		return path;
	}
}

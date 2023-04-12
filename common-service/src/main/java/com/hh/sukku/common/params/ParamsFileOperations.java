package com.hh.sukku.common.params;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 10:30:07 pm
 */

@PropertySource("classpath:file-operation.properties")
@Component
@Getter @Setter
public class ParamsFileOperations {
	
	@Value("${file.upload.path.user}")
	private String fileUploadPathUser;
	
	@Value("${file.upload.path.user}")
	private String fileUploadPathProduct;
}

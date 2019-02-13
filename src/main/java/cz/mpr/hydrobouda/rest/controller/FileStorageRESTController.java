package cz.mpr.hydrobouda.rest.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.mpr.hydrobouda.rest.response.UploadFileResponse;
import cz.mpr.hydrobouda.service.FileSystemStorageService;

/**
 * REST controller for file storage feature.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/files")
public class FileStorageRESTController {
    private static final Logger logger = LoggerFactory.getLogger(FileStorageRESTController.class);

    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    
    @GetMapping
    public List<String> listFiles() {
    	List<Path> files = fileSystemStorageService.loadAll().collect(Collectors.toList());
    	List<String> filesUris = files.stream().map(path -> {
    		return ServletUriComponentsBuilder
    				.fromCurrentContextPath()
    				.path("/files/download/")
    				.path(path.getFileName().toString())
    				.toUriString();	
    	}).collect(Collectors.toList());
    	return filesUris;
    }
    
    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = fileSystemStorageService.store(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(filename)
                .toUriString();

        return new UploadFileResponse(filename, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/upload-multiple")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileSystemStorageService.loadAsResource(filename);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.error("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

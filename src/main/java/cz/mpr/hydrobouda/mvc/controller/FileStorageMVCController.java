package cz.mpr.hydrobouda.mvc.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.mpr.hydrobouda.exception.StorageFileNotFoundException;
import cz.mpr.hydrobouda.service.IStorageService;

// TODO - MPR - #4 - finish implementation of this MVC controller and resolve problems
/**
 * MVC controller for file storage feature.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@Controller
public class FileStorageMVCController {

	private final IStorageService storageService;

	@Autowired
	public FileStorageMVCController(IStorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/filestorage")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll()
				.map(path -> MvcUriComponentsBuilder
						.fromMethodName(FileStorageMVCController.class, "serveFile", path.getFileName().toString())
						.build().toString())
				.collect(Collectors.toList()));

		return "filestorage";
	}

	@GetMapping("/filestorage/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/filestorage")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/filestorage";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}

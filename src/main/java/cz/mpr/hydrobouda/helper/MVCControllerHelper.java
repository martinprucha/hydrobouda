package cz.mpr.hydrobouda.helper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

/**
 * This class contains useful helper methods for MVC controllers.
 * 
 * @author MPR
 * @version 1.0
 *
 */
public class MVCControllerHelper {
	
	/**
	 * This method adds a sequence of integers <0, numberOfPages - 1> representing individual pages 
	 * to model as a new attribute based on content of Page parameter.
	 * 
	 * @param page
	 * @param attributeName
	 * @param model
	 */
	public static <T> void addPageNumbersToModel(Page<T> page, String attributeName, Model model) {
		int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1).boxed().collect(Collectors.toList());
            model.addAttribute(attributeName, pageNumbers);
        }
	}
}

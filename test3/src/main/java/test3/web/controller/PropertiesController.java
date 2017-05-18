package test3.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@RestController
public class PropertiesController extends WebApplicationObjectSupport {

	@Value("${prop.overwrite.test}")
	String overwrite;

	@GetMapping("/overwrite_props")
	String overwrite() {
		return overwrite;
	}
}

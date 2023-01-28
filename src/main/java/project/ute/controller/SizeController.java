package project.ute.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.ute.service.SizeService;

@RestController
@CrossOrigin
public class SizeController {
	@Autowired
	SizeService sizeService;

	@RequestMapping(value = "/api/size/show-all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> showAllSize(@RequestParam("pageNo")Optional<Integer> pageNo) {
		return ResponseEntity.status(HttpStatus.OK).body(sizeService.paging(pageNo));
	}

	@RequestMapping(value = "/api/size/show-with-size", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getSizeByName() {
		return ResponseEntity.status(HttpStatus.OK).body(sizeService.getSizeByName("M", 20000));
	}
}
	
package com.korinek.MeteorologicalDataApp.controller;

import com.korinek.MeteorologicalDataApp.model.City;
import com.korinek.MeteorologicalDataApp.service.CityService;
import com.korinek.MeteorologicalDataApp.service.ImportExportService;
import com.korinek.MeteorologicalDataApp.service.MeasurementService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path="api")
public class ImportExportController {

    private final ImportExportService importExportService;
    private final CityService cityService;

    private static final Logger log = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    public ImportExportController(ImportExportService importExportService, CityService cityService) {
        this.importExportService = importExportService;
        this.cityService = cityService;
    }

    @GetMapping(path=AppPaths.EXPORT_FILE)
    public ResponseEntity<Resource> exportFile(@PathVariable("id") int id){
        try{
            City city = this.cityService.getCity(id);
            String fileName = city.getName() + ".csv";
            String fileContent = this.importExportService.exportFile(city);

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            ByteArrayResource resource = new ByteArrayResource(fileContent.getBytes());

            return ResponseEntity.ok().headers(header).contentLength(fileContent.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path=AppPaths.IMPORT_FILE)
    public ResponseEntity<Resource> importFile(@RequestParam("file") MultipartFile file, @PathVariable("id") int cityId){
        try{
            City city = this.cityService.getCity(cityId);
            this.importExportService.importFile(file, city);
            return ResponseEntity.ok().body(null);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

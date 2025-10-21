package org.app.apidemo.resource;

import lombok.extern.slf4j.Slf4j;
import org.app.apidemo.dto.ReporteDto;
import org.app.apidemo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ReporteApi {
    @Autowired
    private ReporteService reporteService;
    @PostMapping(value = "reporte/generar")
    public ResponseEntity<?> generarReporte(@RequestBody ReporteDto dto) {
        try {
            log.info("parametros {}", dto.toString());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(reporteService.generarPDF(dto));
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}

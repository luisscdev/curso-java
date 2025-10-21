package org.app.apidemo.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.app.apidemo.common.SisVars;
import org.app.apidemo.dto.ReporteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ReporteService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generarPDF(ReporteDto dto) throws IOException, JRException, SQLException {

        // Configurar parámetros del reporte
        Map<String, Object> parametros = new HashMap<>();
        if (dto.getParametros() != null) {
            parametros.putAll(dto.getParametros());
        }

        parametros.put("SUBREPORT_DIR", SisVars.reportes);
        parametros.put("PIE_PAGINA", SisVars.PIE_PAGINA_ENTIDAD);
        parametros.put("MARCA_AGUA", SisVars.MARCA_AGUA_ENTIDAD);
        parametros.put("LOGO", SisVars.LOGO_ENTIDAD);

        Resource fileResource = resourceLoader.getResource(SisVars.reportes + dto.getNombreReporte() + ".jasper");

        try (InputStream reportStream = fileResource.getInputStream()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, dataSource.getConnection());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            log.error("Error al generar el PDF del reporte {}: {}", dto.getNombreReporte(), e.getMessage(), e);
            throw e;
        }
    }
}

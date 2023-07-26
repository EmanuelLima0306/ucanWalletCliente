/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import com.lowagie.text.pdf.PdfWriter;
import connection.IConnectionFactory;
import org.apache.jasper.Constants;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author Emanuel Lima
 */
public abstract class GenericReport {

    public static void report(HttpServletResponse response,
            String relatorioPath, ServletContext context,
            String logoPath,HashedMap parametro) throws Exception {

        Connection connection = new IConnectionFactory().getConnection();

        File file = new File(relatorioPath).getAbsoluteFile();
        File fileLogo = new File(logoPath).getAbsoluteFile();
        String caminho = file.getAbsolutePath();
        System.out.println(caminho);

        try {

//            parametro.put("LOGO", fileLogo.getAbsolutePath());
            
//            InputStream inputStream = new FileInputStream(new File(caminho));
            
//            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//            String caminho = context.getRealPath(relatorioPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, parametro);
            
            
            // Exportar o relatório para PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Configurar a resposta HTTP
            response.setContentType("application/pdf");
            response.setContentLength(pdfBytes.length);

            // Enviar o PDF como resposta ao navegador
            response.getOutputStream().write(pdfBytes);
            
            // create a PDF file
//            byte bytes[] = JasperExportManager.exportReportToPdf(jasperPrint);
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            ServletOutputStream ouputStream = response.getOutputStream();
//            ouputStream.write(bytes, 0, bytes.length);
//            ouputStream.flush();
//            ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public static void reportDireito(HttpServletResponse response,
            String relatorioPath,
            String logoPath,HashedMap parametro) throws Exception {

        Connection connection = new IConnectionFactory().getConnection();

        File file = new File(relatorioPath).getAbsoluteFile();
        File fileLogo = new File(logoPath).getAbsoluteFile();
        String caminho = file.getAbsolutePath();
       

        try {

            parametro.put("LOGO", fileLogo.getAbsolutePath());
            System.out.println(caminho);
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, parametro, connection);

            JasperPrintManager.printReport(jasperPrint, true);
           //Imprime printReport(endereco\relatorio,false);
//	    JasperPrintManager.printReport(file.getAbsolutePath(),true);
            
            // create a PDF file
            byte bytes[] = JasperExportManager.exportReportToPdf(jasperPrint);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void reportDirect(HttpServletResponse response, 
            String relatorioPath,
            String logoPath,  HashedMap parametro) throws Exception {

        Connection connection = new IConnectionFactory().getConnection();

        File file = new File(relatorioPath).getAbsoluteFile();
        File fileLogo = new File(logoPath).getAbsoluteFile();
        String caminho = file.getAbsolutePath();
      

        try {

            parametro.put("LOGO", fileLogo.getAbsolutePath());
            JasperFillManager.fillReport(caminho, parametro, connection);
            System.out.println(caminho);
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, parametro, connection);

            JasperPrintManager.printReport(jasperPrint, true);
            // create a PDF file
//            byte bytes[] = JasperExportManager.exportReportToPdf(jasperPrint);
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
//            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
            System.out.println("fim");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void printJasper(  HttpServletResponse response, 
            List<Object> listData,HashMap<String,Object> parametro,String relatorioPath) {

        JasperReport compiledTemplate = null;
        JRExporter exporter = null;
        ByteArrayOutputStream out = null;
        ByteArrayInputStream input = null;
        BufferedOutputStream output = null;

//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        try {

           
      
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listData);
         
            File filePath = new File(relatorioPath);
            FileInputStream file = new FileInputStream(filePath.getAbsolutePath());
            compiledTemplate = (JasperReport) JRLoader.loadObject(file);

            out = new ByteArrayOutputStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledTemplate, parametro, beanCollectionDataSource);

            exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();

            input = new ByteArrayInputStream(out.toByteArray());

            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(out.toByteArray().length));
            response.setHeader("Content-Disposition", "inline; filename=\"fileName.pdf\"");
            output = new BufferedOutputStream(response.getOutputStream(), Constants.DEFAULT_BUFFER_SIZE);

            byte[] buffer = new byte[Constants.DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();

        } catch (Exception exception) {
            /* ... */
            exception.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
                System.out.println("fim");
            } catch (Exception exception) {
                /* ... */
                exception.printStackTrace();
            }
        }
      
    }
    
    public static void pdf(String nomeJasper, HashMap parameters, List<?> dataSource, String nameRelatorio) throws Exception {

//        TbParametrosGeraisMutue parametroRelatorio = genericReportManaged.findParametroById(52);
//        TbParametrosGeraisMutue parametroRelatorio = genericReportManaged.findParametroById(22);
      

        String pathRelatorio = nomeJasper;


                FacesContext facesContext = FacesContext.getCurrentInstance();
//                facesContext.responseComplete();

                pathRelatorio =  nomeJasper;
                JasperPrint jasperPrint = JasperFillManager.fillReport(pathRelatorio, parameters, new JRBeanCollectionDataSource(dataSource));

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nomeJasper + ".pdf");
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);

                exporter.exportReport();
                exporter.exportReport();
                byte[] bytes = baos.toByteArray();

                if (bytes != null && bytes.length > 0) {
                    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                    response.setContentType("application/pdf");
                    //Metodo que faz a conversao e baixa os documentos em pdf
                    response.setContentType("application/vnd.ms-pdf");
                    response.setHeader("Content-disposition", "inline; filename=\"relatorio.pdf\"");
                    response.setContentLength(bytes.length);

                    try (ServletOutputStream outputStream = response.getOutputStream()) {
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.flush();
                    }
                }

        
    }
    
}

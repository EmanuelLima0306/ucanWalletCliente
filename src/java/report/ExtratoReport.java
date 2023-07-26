/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import connection.IConnectionFactory;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;
import model.ContaModel;
import model.TransacaoModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author Emanuel Lima
 */
public class ExtratoReport extends GenericReport {

    public static void conta(HttpServletResponse response, ServletContext context, ContaModel contaModel) {

        String relatorio = "relatorio/extrato.jasper";
        File file = new File(relatorio);
        if(file.isFile()){
            System.out.println("Ficheiro Encontrado");
        }else{
            System.out.println("Ficheiro Não Encontrado");
        }
        String logo = "relatorio/Logo.jpg";
        try {
            printJasper(response, new ArrayList<>(), new HashMap<>(), relatorio);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ExtratoReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void byArmazemDetalhado(HttpServletResponse response, Integer idArmazem) {

        String relatorio = "relatorio/ListaExistenciaPorArmazemDetalhada.jasper";
        try {
            String logo = "relatorio/Logo.jpg";
            HashedMap parametro = new HashedMap();
            parametro.put("ARMAZEM", idArmazem);
//            report(response, relatorio, logo, parametro);
        } catch (Exception ex) {
            Logger.getLogger(ExtratoReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

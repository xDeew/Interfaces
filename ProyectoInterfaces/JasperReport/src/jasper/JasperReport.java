package jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReport {

    public static void main(String[] args) {
        JasperPrint informeLleno = ReportGenerator.generarInformePorCiudad();
        if (informeLleno != null) {
            JasperViewer visor = new JasperViewer(informeLleno, false);
            visor.setVisible(true);
            try {
                JasperExportManager.exportReportToPdfFile(informeLleno, "clientesporciudad_luiz.pdf");
            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo generar el informe.");
        }
    }
}
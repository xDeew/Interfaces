package jasper;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {

	public static final String INFORME_POR_CIUDAD = "clientesRegistro.jasper";

    public static JasperPrint generarInformePorCiudad() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("FECHA_INFORME", new Date()); 

        try {
            JasperPrint informeLleno = JasperFillManager.fillReport(INFORME_POR_CIUDAD, parametros, Conexion.getMySQLConnetcion());
            return informeLleno;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
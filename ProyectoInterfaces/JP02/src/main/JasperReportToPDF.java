package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportToPDF {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/practica_jasper";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String JRXML_FILE_PATH = "clientesRegistro.jrxml";
	private static final String PDF_OUTPUT_PATH = "./out/clientesPorCiudad_LuizAndre.pdf";

	public static void main(String[] args) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			JasperReport jasperReport = JasperCompileManager.compileReport(JRXML_FILE_PATH);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("FECHA_INFORME", new java.sql.Date(System.currentTimeMillis()));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, PDF_OUTPUT_PATH);

			System.out.println("Informe exportado a PDF exitosamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
		} catch (JRException e) {
			System.out.println("Error al generar/exportar el informe: " + e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
				}
			}
		}
	}
}
package crearExcel;

import compare.laptopPO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import setup.UiElement;
import setup.WebAutomator;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static setup.UiElement.automator;

public class crearExcel extends laptopPO {

    public static void main(String args[]) {}
    @Test
        //Crear libro de trabajo en blanco
    public static void crearExcel(){
        Workbook workbook = new HSSFWorkbook();

        //Crea hoja nueva
        Sheet sheet = workbook.createSheet("Hoja de datos");
        UiElement userRating = automator.find(By.xpath("//table/tbody/tr/th[1]"));
        String aux_userRating =userRating.obtenerTexto();
        UiElement display = automator.find(By.xpath("//table/tbody/tr[2]/th[1]"));
        String aux_display =display.obtenerTexto();
        UiElement Storage = automator.find(By.xpath("//table/tbody/tr[3]/th[1]"));
        String aux_Storage =display.obtenerTexto();
        UiElement Procesor = automator.find(By.xpath("//table/tbody/tr[4]/th[1]"));
        String aux_Procesor =display.obtenerTexto();
        UiElement Ram = automator.find(By.xpath("//table/tbody/tr[5]/th[1]"));
        String aux_Ram =display.obtenerTexto();
        UiElement rating2 = automator.find(By.xpath("//table/tbody/tr[1]/td[1]/span"));
        String aux_rating2 =userRating.obtenerTexto();
        UiElement display2 = automator.find(By.xpath("//table/tbody/tr[2]/td[1]"));
        String aux_display2 =display.obtenerTexto();
        UiElement display3 = automator.find(By.xpath("//table/tbody/tr[2]/td[2]"));
        String aux_display3 =display.obtenerTexto();
        UiElement display4 = automator.find(By.xpath("//table/tbody/tr[2]/td[3]"));
        String aux_display4 =display.obtenerTexto();
        UiElement Storage2 = automator.find(By.xpath("//table/tbody/tr[3]/td[1]"));
        String aux_Storage2 =display.obtenerTexto();
        UiElement Storage3 = automator.find(By.xpath("//table/tbody/tr[3]/td[2]"));
        String aux_Storage3 =display.obtenerTexto();
        UiElement Storage4 = automator.find(By.xpath("//table/tbody/tr[3]/td[3]"));
        String aux_Storage4 =display.obtenerTexto();
        UiElement Procesor2 = automator.find(By.xpath("//table/tbody/tr[4]/td[1]"));
        String aux_Procesor2 =display.obtenerTexto();
        UiElement Procesor3 = automator.find(By.xpath("//table/tbody/tr[4]/td[2]"));
        String aux_Procesor3 =display.obtenerTexto();
        UiElement Procesor4 = automator.find(By.xpath("//table/tbody/tr[4]/td[3]"));
        String aux_Procesor4 =display.obtenerTexto();
        UiElement Ram2 = automator.find(By.xpath("//table/tbody/tr[5]/td[1]"));
        String aux_Ram2 =display.obtenerTexto();
        UiElement Ram3 = automator.find(By.xpath("//table/tbody/tr[5]/td[2]"));
        String aux_Ram3 =display.obtenerTexto();
        UiElement Ram4 = automator.find(By.xpath("//table/tbody/tr[5]/td[3]"));
        String aux_Ram4 =display.obtenerTexto();


        Map<String, Object[]> datos = new TreeMap<String, Object[]>();
        datos.put("1", new Object[]{aux_userRating, aux_rating2});
        datos.put("2", new Object[]{aux_display, aux_display2, aux_display3,aux_display4});
        datos.put("3", new Object[]{aux_Storage, aux_Storage2, aux_Storage3,aux_Storage4});
        datos.put("4", new Object[]{aux_Procesor, aux_Procesor2, aux_Procesor3,aux_Procesor4});
        datos.put("5", new Object[]{aux_Ram, aux_Ram2, aux_Ram3,aux_Ram4});


        //Iterar sobre datos para escribir en la hoja
        Set keyset = datos.keySet();
        int numeroRenglon = 0;
        for (Object key : keyset) {
            Row row = sheet.createRow(numeroRenglon++);
            Object[] arregloObjetos = datos.get(key);
            int numeroCelda = 0;
            for (Object obj : arregloObjetos) {
                Cell cell = row.createCell(numeroCelda++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Se genera el documento
            System.out.println("Excel creado");
            String ruta =
                    "src"
                            + File.separator
                            + "test"
                            + File.separator
                            + "resources"
                            + File.separator
                            + "output"
                            + File.separator;


            FileOutputStream out = new FileOutputStream(new File(ruta+"Summary.xls"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

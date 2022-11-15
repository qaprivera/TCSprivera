package compare;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import setup.Configuration;
import setup.UiElement;

import static setup.Configuration.LAPTOP_1;
import static setup.Configuration.LAPTOP_2;
import crearExcel.crearExcel;


public class laptopTest extends laptopPO{
    WebDriver driver;
    @Test
    @Description("Compara dos laptop y la data obtenida pasarla a un excel,excel queda en el directorio resources,output")
public void compararLaptop() throws Exception {
        irWeb();
        seleccionarSearchLaptop(LAPTOP_1);
        clickLeonovo_1();
        selectLaptop();
        seleccionarLaptop2(LAPTOP_2);
        clickLeonovo_2();
        cickEnComparar();
        verSummary();
        crearExcel.crearExcel();
        cerrarSesion();

}

}

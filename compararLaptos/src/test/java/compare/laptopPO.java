package compare;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import setup.Browser;
import setup.Configuration;
import setup.UiElement;
import setup.WebAutomator;
import crearExcel.crearExcel;
import java.util.HashMap;

import static setup.UiElement.automator;

public class laptopPO  {

    public static void irWeb() throws Exception {

        HashMap<String,String> capabilites = new HashMap<>();
        capabilites.put("headless","false");
        automator = new WebAutomator(Browser.CHROME, capabilites);
        automator.abrirPagina(Configuration.BASE_URL);


    }


    @Description("Seleccionar  Caja desplegable SearchLaptop")
    public void  seleccionarSearchLaptop(String laptop){
       // UiElement search =automator.find(By.cssSelector("div[class=\"_3--CS\"]>span[class=\"_1WJ4c\"]"));
       // UiElement search =automator.find(By.cssSelector("div[class=\"_3--CS\"]>i[class=\"rxUKy\"]"));
       // UiElement.posicionarSobreElemento(search);
        UiElement search = automator.find(new By.ByXPath("//*[@id=\"app\"]/div[3]/div[5]/ul/li[1]/div/div/div/i"));
        search.clicElemento();
        search.teclado(laptop);

    }
    public void  seleccionarLaptop2(String laptop2){
        //UiElement search = automator.find(new By.ByXPath("//*[@id=\"app\"]/div[3]/div[5]/ul/li[1]/div/div/div/i"));
       // UiElement search = automator.find(new By.ByXPath("//*[@id=\"app\"]/div[3]/div[5]/ul/li[2]/div/div/div/span[2]/div/div/div[1]/input"));
        UiElement search = automator.find(By.cssSelector("input[placeholder=\"Search Laptops\"]"));
        search.clicElemento();
        search.teclado(laptop2);



    }
    public void clickLeonovo_1(){
        UiElement leonovo = automator.find(By.cssSelector("img[alt=\"Lenovo E41-80\"]"));
       leonovo.clicElemento();

    }
    public void clickLeonovo_2(){
        UiElement leonovo = automator.find(By.cssSelector("img[alt=\"Lenovo B40-80\"]"));
        leonovo.clicElemento();

    }
    public  void cickEnComparar(){
        UiElement comparar = automator.find(By.cssSelector("button[type=\"button\"][class=\"theme_button \"]"));
        comparar.clicElemento();
    }

    public  void  verSummary(){
        UiElement summary = automator.find(By.cssSelector("h2[class=\"_24YRq _3gJTi\"]"));
        if (summary !=null){
            System.out.println("Tabla summary");

        }
    }
    public void selectLaptop(){
        UiElement selectLaptop =automator.find(By.xpath("/html/body/div[2]/div[3]/div[5]/ul/li[2]/div/div/div/i"));
      selectLaptop.clicElemento();
    }
    public void cerrarSesion(){
        automator.cerrarNavegador();
    }

}
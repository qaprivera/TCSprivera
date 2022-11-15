package setup;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UiElement {
    public static WebAutomator automator;
    private static WebElement element;

    public UiElement(WebAutomator automator, WebElement element) {
        this.automator = automator;
        this.element = element;
    }

    public static void ingresarTexto(String text) {
        automator.getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
        System.out.println("Ingresando texto: " + text);

        //assertEquals(text, this.element.getAttribute("value"), "Assert that entered text is correct.");
    }

    public UiElement clicElemento(){
        automator.getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        System.out.println("Haciendo clic en elemento: " + element);
        return null;
    }

    public void submit(By by) {
        element.submit();
        automator.assertPresent(by);
    }

    public void posicionarSobreElemento(){
        automator.getWait().until(ExpectedConditions.visibilityOf(element));
        Actions action = new Actions(automator.getDriver());
        action.moveToElement(element);
        action.build().perform();
    }

    public void posicionarSobreElemento(By locator){
        automator.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        Actions action = new Actions(automator.getDriver());
        action.moveToElement(element);
        action.build().perform();
    }
    public static void escape(){

        Actions actions = new Actions(automator.getDriver());
        actions.sendKeys(Keys.ESCAPE);
        actions.perform();
        System.out.println("clic en escape");


    }

    public String getLink() {
        return element.getAttribute("href");
    }

    public String obtenerParrafo(){
        return element.getText();
    }
    public String obtenerTexto(){
        automator.getWait().until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public void moverCoordenadasElemento(){
        Actions actions = new Actions(automator.getDriver());
        actions.moveByOffset(element.getLocation().getX()+element.getSize().getWidth()/2,
                element.getLocation().getY()+element.getSize().getHeight()/2);
    }
    public void esperarElementoSeaClickeable(){
        automator.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public void teclado(String teclado){
        Actions actions = new Actions(automator.getDriver());
        actions.sendKeys(teclado);
        actions.build().perform();
    }
}

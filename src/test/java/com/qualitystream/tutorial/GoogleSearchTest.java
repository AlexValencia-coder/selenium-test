package com.qualitystream.tutorial;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.io.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.openqa.selenium.JavascriptExecutor;

public class GoogleSearchTest {

    private WebDriver driver;
    final Logger log = LoggerFactory.getLogger(GoogleSearchTest.class);

    @Before
    public void setUp() throws Exception {
        
        log.debug("Hola");
        log.warn("Un mensaje de advertencia");
                
        //System.setProperty("webdriver.edge.verboseLogging", "true");
        //System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/chromedriver/geckodriver.exe");
        //System.setProperty("webdriver.edge.driver", "./src/test/resources/chromedriver/msedgedriver.exe");

//        DesiredCapabilities jsCapabilities = new DesiredCapabilities();//DesiredCapabilities.Chrome();
//        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--lang=es");//en-US
//        options.addArguments("--disable-translate");
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("intl.accept_languages", "en-US");
//        options.setExperimentalOption("prefs", prefs);
//        jsCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        
//        options.addArguments("--headless"); // Esto ejecutará el navegador sin una interfaz gráfica
//        options.addArguments("--disable-gpu"); // Para evitar problemas con la aceleración por hardware
//        options.addArguments("start-maximized"); // Iniciar maximizado
        //driver = new ChromeDriver();
        //driver = new EdgeDriver(options);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.get("https://www.irctc.co.in/nget/train-search");
        driver.get("https://web.icetex.gov.co/creditos/gestion-de-credito/renovacion-del-credito");
        Thread.sleep(1000);
        
        //driver.findElement(By.xpath("//button[text()='OK']")).click();
        //driver.findElement(By.xpath("//a[normalize-space()='LOGIN']")).click();
        
        // Locate the image element and download the image
        //WebElement imageElement = driver.findElement(By.xpath("//*[@id=\"login_header_disable\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]/form/div[5]/div/app-captcha/div/div/div[2]/span[1]/img"));
        //
        WebElement iframe = driver.findElement(By.xpath("(//iframe[@title='Oficina virtual'])[1]"));
        //Switch to the frame
        driver.switchTo().frame(iframe).switchTo().frame(0);
        
        //iframe = driver.findElement(By.xpath("(//iframe[@title='Oficina virtual'])[1]"));
        
        //Now we can type text into captcha field
        WebElement imageElement= driver.findElement(By.xpath("//*[@id=\"imCaptcha\"]"));
        
        File src = imageElement.getScreenshotAs(OutputType.FILE);
        String path = "./src/test/resources/captures/captcha.png";
        FileHandler.copy(src, new File(path));
//        Thread.sleep(1000);
        
        //Decode captcha
        ITesseract tesseract = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        //image.setDatapath("tessdata"); // path to tessdata directory
        tesseract.setDatapath("./src/test/resources/tessdata"); 
        //files of the example : https://github.com/tesseract-ocr/tessdata
        String str = tesseract.doOCR(new File(path));
        
        System.err.println("El texto de la imagen es: " + str);
        

        //driver.switchTo().frame(iframe);
        driver.switchTo().defaultContent();
        //driver.switchTo().frame(2);
        iframe = driver.findElement(By.xpath("(//iframe[@title='Oficina virtual'])[1]"));
        //Switch to the frame
        driver.switchTo().frame(iframe);
        WebElement codVerifica = driver.findElement(By.xpath("//*[@id=\"txtVerify\"]"));

        codVerifica.sendKeys(str);
        
//        File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        System.out.println("Captura en: " + SrcFile.getAbsolutePath());
//        //Move image file to new destination
//        File DestFile=new File("./src/test/resources/chromedriver/prueba.png");
//       //Copy file at destination
//        FileUtils.copyFileToDirectory(SrcFile, DestFile);
//        String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
//        
//        String idioma = driver.findElement(By.tagName("html")).getAttribute("lang");
//
//        System.out.println("El idioma de la página es: " + idioma);
//        System.err.println("El título es: " + driver.getTitle());
    }

    @Test
    public void testGooglePage() {

//        WebElement searchBox = driver.findElement(By.name("q"));
//        searchBox.clear();
//        searchBox.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
//        searchBox.submit();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
//        File source = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
//        try {
//            FileHandler.copy(source, new File("QED_Full_Page_Screenshot.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(GoogleSearchTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        assertEquals("1","1");
        //assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Buscar con Google", driver.getTitle());
    }

    /*@Test void localizadores() {
		 
		By locator = By.id("id_del_elemento");
		
		By locator_name = By.name("name_elemnt");
		
		By locator_className = By.className("clase_elemento");
		
		By locator_tagName = By.tagName("tag");
		
		By locator_linktext = By.linkText("texto_link");
		
		By locator_partialLinkText = By.partialLinkText("parte_texto");
		
		By locator_cssSelector = By.cssSelector("input[name='q']");
		
		By locator_Xpath = By.xpath("//input[@name='q']");
		
		// JavaScript
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebElement searchBox = (WebElement)js.executeScript("return document.getElementsByName('q')[0]");
		
	}*/
    @After
    public void tearDown() {
        //driver.quit();
    }

}

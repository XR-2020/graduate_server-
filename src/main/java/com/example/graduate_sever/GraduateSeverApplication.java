package com.example.graduate_sever;

import com.example.graduate_sever.common.WebConfig;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.common.WebSiteDateConfig;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.internal.WrapsDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@SpringBootApplication
public class GraduateSeverApplication {

    public static String saveImgToLocal(WebDriver driver) throws Exception {
        String code=null;
        driver.get("http://jx.zut.edu.cn/login.jsp");
        driver.manage().window().setSize(new Dimension(800, 600));
        WebElement element = driver.findElement(By.tagName("img"));
        //创建全屏截图
//        WrapsDriver wrapsDriver = (WrapsDriver) element;
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("e:\\picture\\1.png"));
        try {
            BufferedImage image = ImageIO.read(screen);
            //创建一个矩形使用上面的高度，和宽度
            Point p = element.getLocation();
            //元素坐标
            BufferedImage img = image.getSubimage(p.getX(), p.getY(), element.getSize().getWidth(), element.getSize().getHeight());
            ImageIO.write(img, "png", screen);
            FileUtils.copyFile(screen, new File("E:\\picture\\" + "imgname.png"));
            code=executeTess4J("E:\\picture\\imgname.png");
            System.out.println(code.substring(0,4));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebElement username=driver.findElement(By.id("inputEmail3"));
        WebElement password=driver.findElement(By.id("inputPassword3"));
        WebElement rand=driver.findElement(By.name("rand"));
        username.sendKeys(WebConfig.getUsername());
        Thread.sleep(60);
        password.sendKeys(WebConfig.getPassword());
        Thread.sleep(60);
        rand.sendKeys(code.substring(0,4));
        driver.findElement(By.tagName("button")).click();
        WebDriver.Options manage = driver.manage();
        Set<Cookie> cookies = manage.getCookies();
        String webCookie=null;
        for(Cookie c : cookies){
             webCookie=c.getName()+"="+c.getValue();
        }
        return webCookie;
    }

    public static String executeTess4J(String imgUrl){
        String ocrResult = "";
        try{
            ITesseract instance = new Tesseract();
            //instance.setLanguage("chi_sim");
            File imgDir = new File(imgUrl);
            //long startTime = System.currentTimeMillis();
            ocrResult = instance.doOCR(imgDir);
        }catch (TesseractException e){
            e.printStackTrace();
        }
        return ocrResult;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GraduateSeverApplication.class, args);
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\.cache\\selenium\\chromedriver\\win32\\99.0.4844.51\\chromedriver.exe");
////        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless");
////        WebDriver driver=new ChromeDriver(options);
        WebDriver driver=new ChromeDriver();
        WebCookie.setCookie(saveImgToLocal(driver));
        WebSiteDateConfig.WebSiteDateConfig();
    }

}

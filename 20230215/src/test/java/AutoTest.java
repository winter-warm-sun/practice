import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class AutoTest {
    ChromeDriver driver=new ChromeDriver();
    public void test() throws InterruptedException {
        driver.get("https://www.baidu.com");
        Thread.sleep(1000);
        String text=driver.findElement(By.cssSelector("#hotsearch-content-wrapper > li:nth-child(4) > a > span.title-content-title")).getText();
        System.out.println("获取到的文本："+text);
        System.out.println("type:"+driver.findElement(By.cssSelector("#su")).getAttribute("type"));
        System.out.println("id:"+driver.findElement(By.cssSelector("#su")).getAttribute("id"));
        System.out.println("value:"+driver.findElement(By.cssSelector("#su")).getAttribute("value"));
        System.out.println("class:"+driver.findElement(By.cssSelector("#su")).getAttribute("class"));
        driver.quit();
    }

    public void test2() throws InterruptedException {
        driver.get("https://www.baidu.com");
        System.out.println("搜索之前：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#kw")).sendKeys("你好");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(1000);
        System.out.println("搜索之后：");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }

    public void windowControl() throws InterruptedException {
        Thread.sleep(1000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.manage().window().minimize();
        Thread.sleep(3000);
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        driver.manage().window().setSize(new Dimension(1024,888));
        Thread.sleep(2000);
        driver.quit();
    }

    public void switchTest() throws InterruptedException {
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#s-top-left > a:nth-child(6)")).click();
        // 获取当前页面的句柄
        String curHandle=driver.getWindowHandle();
        System.out.println("当前页面的句柄："+curHandle);
        // 先获取所有标签的句柄
        Set<String> handles=driver.getWindowHandles();
        for(String handle:handles) {
            if(handle!=curHandle) {
                driver.switchTo().window(handle);
            }
        }
        driver.findElement(By.cssSelector("#homeSearchForm > span.s_btn_wr > input"));
        driver.quit();
    }

    public void picture() throws InterruptedException, IOException {
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(3000);
        // 屏幕截图（保存现场）
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        // 把屏幕截好的文件放到指定的路径下
        String fileName="my.png";
        FileUtils.copyFile(srcFile,new File(fileName));
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();
    }
}

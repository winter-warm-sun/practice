import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

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
}

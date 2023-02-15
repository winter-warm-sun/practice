import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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

    public void scriptTest() throws InterruptedException {
        driver.get("https://image.baidu.com/");
        Thread.sleep(1000);
        // 执行js命令：让页面置顶/置底  (如果想滑到最下面，值设置的大一些即可)
        driver.executeScript("document.documentElement.scrollTop=500");
        Thread.sleep(1000);
        // 值为0就是顶部
        driver.executeScript("document.documentElement.scrollTop=0");
        Thread.sleep(1000);
        driver.quit();
    }

    public void implicitlyWait() {
        // 添加隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span"));
        driver.quit();
    }

    public void webDriverWait() {
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        // 添加显示等待
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(driver->driver.findElement(By.cssSelector("#\\31  > div > div > div > div > div.cos-row.row-text_Johh7.row_5y9Az > div > a > div > p > span > span")));
        driver.quit();
    }

    public void navigateTest() {
        driver.navigate().to("https://www.baidu.com");
        // 想要回退到访问百度网址之前的状态
        driver.navigate().back();
        // 前进，又进入到了百度首页
        driver.navigate().forward();
        // 刷新百度首页
        driver.navigate().refresh();
        driver.quit();
    }

    public void alertTest() throws InterruptedException {
        driver.get("file:///D:/%E4%BB%A3%E7%A0%81%E4%BB%93%E5%BA%93/selenium-html/Prompt.html");
        Thread.sleep(1000);
        // 打开弹窗
        driver.findElement(By.cssSelector("body > input[type=button]")).click();
        Thread.sleep(1000);
        // 切换到弹窗进行弹窗的处理
        Alert alert=driver.switchTo().alert();
        Thread.sleep(1000);
        // 弹窗输入文本
        alert.sendKeys("这是输入的文本");
        Thread.sleep(1000);
        // 1.点击确认
        alert.accept();
        // 2.点击取消
        // alert.dismiss();
        Thread.sleep(1000);
        driver.quit();
    }

    public void selectTest() throws InterruptedException {
        driver.get("file:///D:/%E4%BB%A3%E7%A0%81%E4%BB%93%E5%BA%93/selenium-html/select.html");
        Thread.sleep(2000);
        WebElement ele=driver.findElement(By.cssSelector("#ShippingMethod"));
        // 先创建选择框对象
        Select select=new Select(ele);
        Thread.sleep(2000);
        // 根据文本来选择
        //select.selectByVisibleText("UPS Next Day Air ==> $12.51");
        // 根据属性值来选择
//        select.selectByValue("12.51");
        // 根据序号来选择
        select.selectByIndex(1);
        Thread.sleep(2000);
        driver.quit();
    }

    public void fileUploadTest() throws InterruptedException {
        driver.get("file:///D:/%E4%BB%A3%E7%A0%81%E4%BB%93%E5%BA%93/selenium-html/upload.html");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > div > div > input[type=file]")).sendKeys("D:\\代码仓库\\selenium-html\\upload.html");
        Thread.sleep(2000);
        driver.quit();
    }


}

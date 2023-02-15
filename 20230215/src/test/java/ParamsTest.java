import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ParamsTest {
    void paramsTest() {
        // 百度搜索迪丽热巴
        // 先创建选项对象，然后再设置浏览器参数
        ChromeOptions options=new ChromeOptions();
        options.addArguments("-headless");
        ChromeDriver driver=new ChromeDriver(options);
        driver.get("https://www.baidu.com");
        driver.findElement(By.cssSelector("#kw")).sendKeys("迪丽热巴");
        driver.findElement(By.cssSelector("#su")).click();
        driver.quit();
    }
}

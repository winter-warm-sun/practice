import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoTest {
    public void test() throws InterruptedException {
        // 创建驱动实例，创建会话（打开浏览器）
        ChromeDriver driver=new ChromeDriver();
        Thread.sleep(1000);

        // 在浏览器里输入百度网址，访问百度首页
        driver.get("https://www.baidu.com");
        Thread.sleep(1000);

        // 找到百度首页输入框元素，并输入关键词“Java”
        driver.findElement(By.cssSelector("#kw")).sendKeys("蔡徐坤");
        Thread.sleep(1000);

        // 找到百度首页“百度一下”按钮，并点击一下
        driver.findElement(By.cssSelector("#su")).click();
        Thread.sleep(1000);

        // 结束会话（关闭浏览器）
        driver.quit();
    }
}

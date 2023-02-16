package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class AutoTest3 {
    @ParameterizedTest
    @ValueSource(strings = {"Jack","Hunter","Bob"})
    void SparamsTest(String name) {
        System.out.println(name);
    }

    @ParameterizedTest
    @CsvSource({"mary,20","lucy,18","jack,21"})
    void manyParamsTest(String name,int age) {
        System.out.println("name:"+name+",age:"+age);
    }

    @ParameterizedTest
    @CsvFileSource(files = "D:\\代码仓库\\test.csv")
    void csvFileTest(String name,int age) {
        System.out.println("name:"+name+",age:"+age);
    }

    // 通过动态方法来提供数据源
    @ParameterizedTest
    @MethodSource("methodParams")
    void dynamicParamsTest(String name,int age) {
        System.out.println("name:"+name+",age:"+age);
    }

    static Stream<Arguments> methodParams() throws InterruptedException {
        // 构造动态参数
        String[] arr=new String[4];
        for (int i=0;i< arr.length;i++) {
            Thread.sleep(500);
            arr[i]=System.currentTimeMillis()+"";
        }
        return Stream.of(
                Arguments.arguments(arr[0],20),
                Arguments.arguments(arr[1],19),
                Arguments.arguments(arr[2],18),
                Arguments.arguments(arr[3],17)
        );
    }
}

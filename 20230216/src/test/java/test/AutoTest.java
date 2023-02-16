package test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutoTest {
    @Order(1)
    @Test
    void testB() {
        System.out.println("1");
    }
    @Order(2)
    @Test
    void testA() {
        System.out.println("2");
    }
    @Order(3)
    @Test
    void testC() {
        System.out.println("3");
    }
}

package dev.devesh.productservice.controllers;

import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;


    @Test
    @DisplayName("1+1 Equals 2")
    void testOnePlusOneEqualsTrue() throws NotFoundException {

//        System.out.println("It is true");
        // Assertion framework
//->allows you to make assertions
//->allows you to make checks
//        assert(11==1+1,"");
//        assertEquals(11,1+1,"One plus is not coming to be 11");

//        assertNull(fakeStoryProductServiceClient.getProductById(101L));
//        assertEquals(null,fakeStoryProductServiceClient.getProductById(101L));
//        assertThrows(NotFoundException.class,()->fakeStoryProductServiceClient.getProductById(101L));
        assertTrue(returnSomething());
    }

    boolean returnSomething() {
        Random random = new Random();
        return random.nextInt() % 2 == 0;
    }

    @Test
    void additionShouldBeCorrect(){

        assertTrue(-1 + -1 == -2,"Adding 2 negatives is not correct");
        assertTrue(-1 + 0 ==  -1,"adding a negative and zero is giving wrong answer");
        assertTrue( -1 + 1 ==   0);
//        assert -1 + -1 == -2;
//        assert -1 + 0 ==  -1;
//        assert -1 + 1 ==   0;
//        assert  1 + 0 ==   1;
//        assert  1 + 1 ==   2;
    }
}



package dev.devesh.productservice.controllers;

import dev.devesh.productservice.dtos.GenericProductDto;
import dev.devesh.productservice.exceptions.NotFoundException;
import dev.devesh.productservice.services.ProductService;
import dev.devesh.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    private ProductController productController;

    @MockBean
    @Autowired
    private ProductService productService;

//    @Test
//    void returnsNullWhenProductDoesntExist() throws NotFoundException {
//
//        when(productService.getProductById(any(Long.class)))
//                .thenReturn(null);
//
//        when(productService.getProductById(121L))
//                .thenReturn(null);
//
//
//        GenericProductDto genericProductDto = productController.getProductById(122L);
//
//        assertNull(genericProductDto);
//
//    }

    @Test
    void returnsSameProductAsServiceWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto=new GenericProductDto();
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(genericProductDto);

        assertEquals(genericProductDto.getPrice(),productController.getProductById(123L).getPrice());

//        assertThrows(NotFoundException.class,()->productController.getProductById(123L));
    }

    @Test
    void throwsExceptionWhenProductDoesntExist() throws NotFoundException {
        when(
                productService.getProductById(any(Long.class))
        )
                .thenReturn(null);

        assertThrows(NotFoundException.class,()->productController.getProductById(123L));
    }

    @Test
    void shouldReturnTitleDeveshWithProductID1() throws NotFoundException {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setTitle("Devesh");

        when(
                productService.getProductById(1L)
        ).thenReturn(genericProductDto);

        GenericProductDto genericProductDto1=productController.getProductById(1L);
        assertEquals("Devesh",genericProductDto1.getTitle());
    }


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



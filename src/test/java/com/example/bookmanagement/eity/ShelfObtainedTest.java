package com.example.bookmanagement.eity;

import com.example.bookmanagement.control.ShelfObtainedController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ShelfObtainedTest {

    private ShelfObtainedController controller;

    @BeforeClass
    public  static  void setUp() throws Exception {
        System.out.println("ShelfObtained 测试开始");
    }

    @Test
    public void testdeleteNum() throws Exception{
        List<String> list=new ArrayList<>();
        list.add("97871112125083");
        list.add("97871112125084");
        System.out.println(controller+"   asdas");
        //controller.deleteBookNum(list);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("ShelfObtained 测试开结束");
    }
}
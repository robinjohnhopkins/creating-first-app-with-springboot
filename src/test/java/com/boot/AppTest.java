package com.boot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.boot.controller.HomeController;
import org.junit.Test;


public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testHomeController()
    {
        HomeController hc = new HomeController();
        String result = hc.home();
        assertEquals("check home controller", result, "Das Boot, reporting for duty!" );
    }
}

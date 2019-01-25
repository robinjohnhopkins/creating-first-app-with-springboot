package com.boot;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShipwreckControllerTest {

    @InjectMocks
    private ShipwreckController sc;

    @Mock
    ShipwreckRepository shipwreckRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckedGet(){
        Shipwreck sw = new Shipwreck();
        sw.setId(1L);

        when(shipwreckRepository.findOne(1L)).thenReturn(sw);

        Shipwreck wreck = sc.get(1L);
        Shipwreck wreck1b = sc.get(1L);
        Shipwreck wreck2 = sc.get(2L);

        //verify(shipwreckRepository).findOne(1L); this verifies shipwreckRepository.findOne(1L) is called once
        // the following verifies it is called twice
        verify(shipwreckRepository, times(2)).findOne(1L);

        assertEquals("check id of first wreck", 1L, wreck.getId().longValue());
        //add hamcrest test
        assertThat(wreck.getId(), is(1L));
   }
}

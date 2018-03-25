package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortinSaldoPalautuuOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRAhaa() {
        assertEquals(true, kortti.otaRahaa(500));
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void SaldoEiVaheneJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(2000));
        assertEquals("saldo: 10.0", kortti.toString());
    }
}

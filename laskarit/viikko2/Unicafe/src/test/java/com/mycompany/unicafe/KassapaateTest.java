/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katamila
 */
public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void konstruktoriAsettaaOikeatArvot() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenKateisostoToimii() {
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(0, kassa.syoEdullisesti(240));
        assertEquals(100480, kassa.kassassaRahaa());
    }

    
    @Test
    public void edullistenLounaidenMaaraKasvaaKateisella() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        kassa.syoEdullisesti(240);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenKateisostoEiToimiRahatta() {
        assertEquals(230, kassa.syoEdullisesti(230));
        assertEquals(0, kassa.syoEdullisesti(0));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasKateisostoToimii() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(0, kassa.syoMaukkaasti(400));
        assertEquals(100800, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaidenLounaidenMaaraKasvaaKateisella() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        kassa.syoMaukkaasti(400);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaidenKateisostoEiToimiRahatta() {
        assertEquals(230, kassa.syoMaukkaasti(230));
        assertEquals(1, kassa.syoMaukkaasti(1));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullinenKorttiostoToimii() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void edullinenKorttiostoKasvattaaLounasmaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiostoEiOnnistuRahatta() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullinenKorttiostoEiMuutaKassaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKorttiostoToimii() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void maukasKorttiostoKasvattaaLounasmaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasKorttiostoEiOnnistuRahatta() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukasKorttiostoEiMuutaKassaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void ladatessaKortinJaKassanArvoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void ladatessaKassanArvoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivinenSummaEiLataaKorttia() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }

}

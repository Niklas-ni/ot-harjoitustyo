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
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }

    @Test
    public void EiMeneMiinukselle() {
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void SaldoVaheneeOikein() {
        kortti.otaRahaa(900);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    @Test
    public void TrueFalse(){
        assertEquals(true, kortti.otaRahaa(900));
        assertEquals(false, kortti.otaRahaa(1200));
    }
    @Test
    public void Saldotesti(){
        assertEquals(1000, kortti.saldo());
    }
}

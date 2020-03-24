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
 * @author niri91
 */
public class KassapaateTest {

    Kassapaate Testikassa;
    Maksukortti kortti;
    Maksukortti Eivaraa;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Testikassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        Eivaraa = new Maksukortti(100);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void EdullisiaLounaitaMaukkaitalounaitaSaldo() {
        assertEquals(0, Testikassa.edullisiaLounaitaMyyty());
        assertEquals(0, Testikassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, Testikassa.kassassaRahaa());
    }

    @Test
    public void EdullinenLounasOsto() {
        Testikassa.syoEdullisesti(1000);
        assertEquals(100240, Testikassa.kassassaRahaa());
        assertEquals(1, Testikassa.edullisiaLounaitaMyyty());
        assertEquals(760, Testikassa.syoEdullisesti(1000));
    }

    @Test
    public void MaukasLounasOsto() {
        Testikassa.syoMaukkaasti(1000);
        assertEquals(100400, Testikassa.kassassaRahaa());
        assertEquals(1, Testikassa.maukkaitaLounaitaMyyty());
        assertEquals(600, Testikassa.syoMaukkaasti(1000));
    }
    @Test
    public void EiRahaaMaukas() {
        Testikassa.syoMaukkaasti(300);
        assertEquals(100000, Testikassa.kassassaRahaa());
        assertEquals(0, Testikassa.maukkaitaLounaitaMyyty());
        assertEquals(300, Testikassa.syoMaukkaasti(300));
    }
    @Test
    public void EiRahaaEdullinen() {
        Testikassa.syoEdullisesti(100);
        assertEquals(100000, Testikassa.kassassaRahaa());
        assertEquals(0, Testikassa.edullisiaLounaitaMyyty());
        assertEquals(100, Testikassa.syoEdullisesti(100));
    }
    @Test
    public void KortillaEdullinen() {
        Testikassa.syoEdullisesti(kortti);
        assertEquals(100000, Testikassa.kassassaRahaa());
        assertEquals(1, Testikassa.edullisiaLounaitaMyyty());
        assertEquals(760,kortti.saldo() );
    }
    
    @Test
    public void KortillaMaukas() {
        Testikassa.syoMaukkaasti(kortti);
        assertEquals(100000, Testikassa.kassassaRahaa());
        assertEquals(1, Testikassa.maukkaitaLounaitaMyyty());
        assertEquals(600, kortti.saldo());
    }
    @Test
    public void KortillaEiRahaaMaukasTrueFalse() {
        assertEquals(false, Testikassa.syoMaukkaasti(Eivaraa));
        assertEquals(true, Testikassa.syoMaukkaasti(kortti));
    }
    @Test
    public void KortillaEiRahaaEdullinenTrueFalse() {
        assertEquals(false, Testikassa.syoEdullisesti(Eivaraa));
        assertEquals(true, Testikassa.syoEdullisesti(kortti));
    }
    @Test
    public void KortillaEiRahaaEdullinenMaara() {
        Testikassa.syoEdullisesti(Eivaraa);
        assertEquals(0, Testikassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void KortillaEiRahaaMaukasMaara() {
        Testikassa.syoMaukkaasti(Eivaraa);
        assertEquals(0, Testikassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void KortilleRahaa() {
        Testikassa.lataaRahaaKortille(Eivaraa, 400);
        assertEquals(100400, Testikassa.kassassaRahaa());
        assertEquals(500, Eivaraa.saldo());
    }
    @Test
    public void KortilleMiinusRahaa() {
        Testikassa.lataaRahaaKortille(Eivaraa, -400);
        assertEquals(100000, Testikassa.kassassaRahaa());
        assertEquals(100, Eivaraa.saldo());
    }
    
}

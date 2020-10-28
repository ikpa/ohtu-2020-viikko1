package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10,5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void huonoVarastoLuoHuononVaraston() {
        Varasto huono = new Varasto(-1.0);
        assertEquals(0.0, huono.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriToimii() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriToimiiKunTilavuusVirheellinen() {
        Varasto huono = new Varasto(-1.0, 0);
        assertEquals(0.0, huono.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriToimiiKunSaldoVirheellinen() {
        Varasto huono = new Varasto(10, -1);
        assertEquals(0.0, huono.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriToimiiKunSaldoEnemmanKuinTilavuus() {
        Varasto uusi = new Varasto(10, 11);
        assertEquals(10, uusi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        String testi = "saldo = 0.0, vielä tilaa 10.0";
        assertEquals(varasto.toString(),testi);
    }
    
    @Test
    public void lisaaVarastoonToimiiKunVirheellinenMaara() {
        varasto.lisaaVarastoon(-1.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonToimiiKunLisataanLiikaa() {
        varasto.lisaaVarastoon(11.0);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaToimiiKunOtetaanVirheellinenMaara() {
        assertEquals(varasto.otaVarastosta(-1.0), 0.0, vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaToimiiKunOtetaanLiikaa() {
        varasto.lisaaVarastoon(4);
        assertEquals(varasto.otaVarastosta(5), 4.0, vertailuTarkkuus);
    }

}
package controller;

import java.util.ArrayList;
import model.TabelaModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TabelaControllerTest {

    public TabelaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }


    @Test
    public void testExcluir() {
        TabelaController tabela = new TabelaController();
        TabelaModel model = new TabelaModel();
        model.setIdJogo(tabela.ultimoIdJogos() + 1);
        model.setPlacar(15);
        assertTrue(tabela.InserirJogos(model));
               
    }


    @Test
    public void testInserir() {
        TabelaController tabela = new TabelaController();
        TabelaModel model = new TabelaModel();
        model.setIdJogo(tabela.ultimoIdJogos());
        assertTrue(tabela.excluirJogo(model));       
    } 
}
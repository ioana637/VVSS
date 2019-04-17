package ccir2082MV.evaluator.controller;

import ccir2082MV.evaluator.exception.DuplicateIntrebareException;
import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.exception.NotAbleToCreateStatisticsException;
import ccir2082MV.evaluator.exception.NotAbleToCreateTestException;
import ccir2082MV.evaluator.model.Intrebare;
import ccir2082MV.evaluator.model.Statistica;
import ccir2082MV.evaluator.repository.IntrebariRepository;
import ccir2082MV.evaluator.validator.IValidator;
import ccir2082MV.evaluator.validator.IntrebariValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppServiceTestTopDown {
    private static AppService appService;
    private static String file = "./src/resources/test1.txt";

    @BeforeClass
    public static void setUpAll() {
        System.out.println("Setup for all subsequent tests...");
        //setup
    }

    @Before
    public void setUp() {
        System.out.println("Setup for each subsequent test...");
        IValidator<Intrebare> validator = new IntrebariValidator();
        IntrebariRepository repository = new IntrebariRepository(validator);
        appService = new AppService(repository);
        try {
            appService.loadIntrebariFromFile(file);
        } catch (IntrebareValidatorFailedException e) {
            System.err.println(e.getMessage());
        }
    }

    @After
    public void teardown() {
        System.out.println("Tear down");
    }

    /*---------------------Testare unitara modul C | C - valid----------------------------------------*/
    @Test
    public void createStatisticsF03_TC2_valid() {
        try {
            Statistica statistica = appService.getStatistica();
            assertTrue(true);
            assertTrue(statistica.getIntrebariDomenii().containsKey("Literatura"));
            assertTrue(statistica.getIntrebariDomenii().get("Literatura") == 1);
        } catch (NotAbleToCreateStatisticsException e) {
            assertTrue(false);
            e.printStackTrace();
        }

    }


    /*-----------------------Testare unitara modul B | B -invalid ---------------------------------------------*/

    @Test(expected = NotAbleToCreateTestException.class)
    public void createNewTestF02_TC01() throws NotAbleToCreateTestException {
        appService.createNewTest();
    }

    /*-----------------------Testare unitara modul A | A -valid ---------------------------------------------*/

    @Test
    public void addNewIntrebareF01_TC1_ECP() {
        try {
            Intrebare intrebare = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "M");
            assertTrue(true);
            assertTrue(appService.exists(intrebare));
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /*-----------------------Testare de integrare TOP DOWN P->A | A-valid ----------------------------------------*/
    @Test
    public void z_topDown_TC01() {
        try {
            Intrebare intrebare = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "M");
            Intrebare intrebare1 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "A");
            Intrebare intrebare2 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "B");
            assertTrue(true);
            assertTrue(appService.exists(intrebare));
            assertTrue(appService.exists(intrebare1));
            assertTrue(appService.exists(intrebare2));
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /*-----------------------Testare de integrare TOP DOWN P->A->B | A,B valid---------------------------------*/
    @Test
    public void z_topDown_TC02() {
        try {
            Intrebare intrebare = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "M");
            Intrebare intrebare1 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "A");
            Intrebare intrebare2 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "B");
            Intrebare intrebare3 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "C");
            assertTrue(true);
            assertTrue(appService.exists(intrebare));
            assertTrue(appService.exists(intrebare1));
            assertTrue(appService.exists(intrebare2));
            assertTrue(appService.exists(intrebare3));

            try {
                ccir2082MV.evaluator.model.Test test = appService.createNewTest();
                assertTrue(test.getIntrebari().contains(intrebare));
                assertTrue(test.getIntrebari().contains(intrebare1));
                assertTrue(test.getIntrebari().contains(intrebare2));
                assertTrue(test.getIntrebari().contains(intrebare3));
                assertTrue(test.getIntrebari().size() == 5);
            } catch (NotAbleToCreateTestException e) {
                assertTrue(false);
            }
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /*-----------------------Testare de integrare TOP DOWN P->A->B->C | A,B,C valid -------------------------------*/
    @Test
    public void z_topDown_TC03() {
        try {
            Intrebare intrebare = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "M");
            Intrebare intrebare1 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "A");
            Intrebare intrebare2 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "B");
            Intrebare intrebare3 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "C");
            assertTrue(true);
            assertTrue(appService.exists(intrebare));
            assertTrue(appService.exists(intrebare1));
            assertTrue(appService.exists(intrebare2));
            assertTrue(appService.exists(intrebare3));

            try {
                ccir2082MV.evaluator.model.Test test = appService.createNewTest();
                assertTrue(test.getIntrebari().contains(intrebare));
                assertTrue(test.getIntrebari().contains(intrebare1));
                assertTrue(test.getIntrebari().contains(intrebare2));
                assertTrue(test.getIntrebari().contains(intrebare3));
                assertTrue(test.getIntrebari().size() == 5);
            } catch (NotAbleToCreateTestException e) {
                assertTrue(false);
            }

            Statistica statistica = appService.getStatistica();
            assertTrue(statistica.getIntrebariDomenii().containsKey("Literatura"));
            assertTrue(statistica.getIntrebariDomenii().get("Literatura")==1);

            assertTrue(statistica.getIntrebariDomenii().containsKey("M"));
            assertTrue(statistica.getIntrebariDomenii().get("M")==1);

            assertTrue(statistica.getIntrebariDomenii().containsKey("A"));
            assertTrue(statistica.getIntrebariDomenii().get("A")==1);

            assertTrue(statistica.getIntrebariDomenii().containsKey("B"));
            assertTrue(statistica.getIntrebariDomenii().get("B")==1);

            assertTrue(statistica.getIntrebariDomenii().containsKey("C"));
            assertTrue(statistica.getIntrebariDomenii().get("C")==1);

        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch (NotAbleToCreateStatisticsException e) {
            assertTrue(false);
        }
    }
}
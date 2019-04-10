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

public class AppServiceTestBigBang {
    private static AppService appService;
    private static String file = "E:\\IdeaProjects\\VVSS\\Lab01\\src\\main\\test1.txt";

    @BeforeClass
    public static void setUpAll() {
        System.out.println("Setup for all subsequent tests...");
        //setup
        IValidator<Intrebare> validator = new IntrebariValidator();
        IntrebariRepository repository = new IntrebariRepository(validator);
        appService = new AppService(repository);
        try {
            appService.loadIntrebariFromFile(file);
        } catch (IntrebareValidatorFailedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Before
    public void setUp() {
        System.out.println("Setup for each subsequent test...");
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

    /*-----------------------Testare big-bang modul P->C->A->B | A,B,C valid---------------------------------------*/

    @Test
    public void z_bigBang_TC01() {
        /*----------------------------Modul C------------------------*/

        try {
            Statistica statistica = appService.getStatistica();
            assertTrue(true);
            assertTrue(statistica.getIntrebariDomenii().containsKey("Literatura"));
            assertTrue(statistica.getIntrebariDomenii().containsKey("M"));
            assertTrue(statistica.getIntrebariDomenii().get("Literatura") == 1);
            assertTrue(statistica.getIntrebariDomenii().get("M") == 1);
        } catch (NotAbleToCreateStatisticsException e) {
            assertTrue(false);
            e.printStackTrace();
        }

        /*----------------------------Modul A------------------------*/
        Intrebare intrebare1 = null, intrebare2 = null, intrebare3 = null;
        try {
            intrebare1 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "A");
            intrebare2 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "B");
            intrebare3 = appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3",
                    "1", "C");
            assertTrue(appService.exists(intrebare1));
            assertTrue(appService.exists(intrebare2));
            assertTrue(appService.exists(intrebare3));
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }

        /*----------------------------Modul B------------------------*/
        try {
            ccir2082MV.evaluator.model.Test test = appService.createNewTest();
            assertTrue(true);
            assertTrue(test.getIntrebari().size() == 5);
            assertTrue(test.getIntrebari().contains(intrebare1));
            assertTrue(test.getIntrebari().contains(intrebare2));
            assertTrue(test.getIntrebari().contains(intrebare3));
        } catch (NotAbleToCreateTestException e) {
            assertTrue(false);
            e.printStackTrace();
        }

    }
}
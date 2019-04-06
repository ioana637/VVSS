package ccir2082MV.evaluator.controller;

import ccir2082MV.evaluator.exception.DuplicateIntrebareException;
import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.exception.NotAbleToCreateTestException;
import ccir2082MV.evaluator.model.Intrebare;
import ccir2082MV.evaluator.repository.IntrebariRepository;
import ccir2082MV.evaluator.validator.IValidator;
import ccir2082MV.evaluator.validator.IntrebariValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AppServiceTest {
    private static AppService appService;
    private static List<String> listOfFiles = new ArrayList();
    private static Integer index;

//    @BeforeClass
//    public static void setUpAll() {
//        System.out.println("Setup for all subsequent tests...");
//        IValidator<Intrebare> validator = new IntrebariValidator();
//        IntrebariRepository repository = new IntrebariRepository(validator);
//        appService = new AppService(repository);
//    }

    @BeforeClass
    public static void setUpAll() {
        System.out.println("Setup for all subsequent tests...");
//setup
        listOfFiles.add("E:\\IdeaProjects\\VVSS\\Lab01\\src\\main\\test1.txt");
        listOfFiles.add("E:\\IdeaProjects\\VVSS\\Lab01\\src\\main\\test2.txt");
        listOfFiles.add("E:\\IdeaProjects\\VVSS\\Lab01\\src\\main\\test3.txt");
        index = 0;
    }

    @Before
    public void setUp() {
        System.out.println("Setup for each subsequent test...");
        IValidator<Intrebare> validator = new IntrebariValidator();
        IntrebariRepository repository = new IntrebariRepository(validator);
        appService = new AppService(repository);
        if (index >14) {
            try {
                appService.loadIntrebariFromFile(listOfFiles.get(index-14));
            } catch (IntrebareValidatorFailedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @After
    public void teardown() {
        System.out.println("Tear down");
        index++;
    }

    /*-----------------------F02!!!---------------------------------------------*/

    @Test(expected = NotAbleToCreateTestException.class)
    public void createNewTestF02_TC01() throws NotAbleToCreateTestException {
        appService.createNewTest();
    }

    @Test(expected = NotAbleToCreateTestException.class)
    public void createNewTestF02_TC02() throws NotAbleToCreateTestException {
        appService.createNewTest();
    }

    @Test
    public void createNewTestF02_TC03() {
        try {
            appService.createNewTest();
            assertTrue(true);
        } catch (NotAbleToCreateTestException e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    /*-----------------------ECP---------------------------------------------*/

    @Test
    public void addNewIntrebareTC1_ECP() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1", "M");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();

            assertTrue(false);
        }
    }

    @Test(expected = IntrebareValidatorFailedException.class)
    public void addNewIntrebareTC5_ECP() throws DuplicateIntrebareException, IntrebareValidatorFailedException {
        appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1", "");
    }

    @Test(expected = IntrebareValidatorFailedException.class)
    public void addNewIntrebareTC4_ECP() throws DuplicateIntrebareException, IntrebareValidatorFailedException {
        appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                "MatematicaMatematicaMatemat1234");
    }

    @Test(expected = NullPointerException.class)
    public void addNewIntrebareTC2_ECP() throws DuplicateIntrebareException, IntrebareValidatorFailedException {
        appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1", null);
    }

    @Test(expected = NullPointerException.class)
    public void addNewIntrebareTC3_ECP() throws DuplicateIntrebareException, IntrebareValidatorFailedException {
        appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", null, "Matematica");
    }

    /*-----------------------BVA---------------------------------------------*/

    @Test
    public void addNewIntrebareTC3_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "Ma");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();

            assertTrue(false);
        }

    }

    @Test
    public void addNewIntrebareTC4_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "MatematicaMatematicaMatemat123");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();

            assertTrue(false);
        }

    }

    @Test
    public void addNewIntrebareTC5_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "MatematicaMatematicaMatemat12");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();

            assertTrue(false);
        }
    }

    @Test
    public void addNewIntrebareTC7_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "A");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void addNewIntrebareTC8_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "B");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void addNewIntrebareTC9_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "C");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /*...................................................*/

    @Test
    public void addNewIntrebareTC32_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "1",
                    "Z");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }


    @Test
    public void addNewIntrebareTC35_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "2",
                    "M");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void addNewIntrebareTC36_BVA() {
        try {
            appService.addNewIntrebare("Enunt?", "1) Raspuns1", "2) Raspuns2", "3) Raspuns3", "3",
                    "M");
            assertTrue(true);
        } catch (DuplicateIntrebareException | IntrebareValidatorFailedException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
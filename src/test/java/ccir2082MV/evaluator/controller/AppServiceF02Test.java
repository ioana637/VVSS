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

import static org.junit.Assert.assertTrue;

public class AppServiceF02Test {
    private static AppService appService;
    private static List<String> listOfFiles = new ArrayList();
    private static Integer index;

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
        try {
            appService.loadIntrebariFromFile(listOfFiles.get(index));
        } catch (IntrebareValidatorFailedException e) {
            System.err.println(e.getMessage());
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

}
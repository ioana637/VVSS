package ccir2082MV.evaluator.main;

import ccir2082MV.evaluator.controller.AppService;
import ccir2082MV.evaluator.exception.DuplicateIntrebareException;
import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.exception.NotAbleToCreateStatisticsException;
import ccir2082MV.evaluator.exception.NotAbleToCreateTestException;
import ccir2082MV.evaluator.model.Intrebare;
import ccir2082MV.evaluator.model.Statistica;
import ccir2082MV.evaluator.model.Test;
import ccir2082MV.evaluator.repository.IntrebariRepository;
import ccir2082MV.evaluator.validator.IValidator;
import ccir2082MV.evaluator.validator.IntrebariValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

    private static final String file = "src/main/intrebari.txt";

    public static void main(String[] args) throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        IValidator<Intrebare> validator = new IntrebariValidator();
        IntrebariRepository repository = new IntrebariRepository(validator);
        AppService appService = new AppService(repository);
        try {
            appService.loadIntrebariFromFile(file);
        } catch (IntrebareValidatorFailedException e) {
            System.err.println(e.getMessage());
        }


        boolean activ = true;
        String optiune = null;

        while (activ) {

            System.out.println("");
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println("");

            optiune = console.readLine();

            switch (optiune) {
                case "1":
                    String enunt, raspuns1, raspuns2, raspuns3, raspunsCorect, domeniu;
                    System.out.println("Introduceti enuntul intrebarii");
                    enunt = console.readLine();
                    System.out.println("Introduceti raspuns 1");
                    raspuns1 = console.readLine();
                    System.out.println("Introduceti raspuns 2");
                    raspuns2 = console.readLine();
                    System.out.println("Introduceti raspuns 3");
                    raspuns3 = console.readLine();
                    System.out.println("Introduceti raspunsul corect");
                    raspunsCorect = console.readLine();
                    System.out.println("Introduceti domeniul intrebarii");
                    domeniu = console.readLine();

                    Intrebare intrebare;
                    try {
                        intrebare = new Intrebare(enunt, raspuns1, raspuns2, raspuns3, raspunsCorect, domeniu);
                        appService.addNewIntrebare(intrebare);
                    } catch (IntrebareValidatorFailedException e) {
                        System.err.println("Intrebarea nu a fost corect introdusa");
                        System.err.println(e.getMessage());
                    } catch (DuplicateIntrebareException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        Test test = appService.createNewTest();
                        System.out.println(test);
                    } catch (NotAbleToCreateTestException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "3":
                    Statistica statistica;
                    try {
                        statistica = appService.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        // TODO
                    }

                    break;
                case "4":
                    activ = false;
                    break;
                default:
                    break;
            }
        }

    }

}

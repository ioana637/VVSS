package ccir2082MV.evaluator.validator;


import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.model.Intrebare;

public class IntrebariValidator implements IValidator<Intrebare>{

    public static void validateEnunt(String enunt) throws IntrebareValidatorFailedException {

        enunt = enunt.trim();

        if (enunt.equals(""))
            throw new IntrebareValidatorFailedException("Enuntul este vid!");
        if (!Character.isUpperCase(enunt.charAt(0)))
            throw new IntrebareValidatorFailedException("Prima litera din enunt nu e majuscula!");
        if (!String.valueOf(enunt.charAt(enunt.length() - 1)).equals("?"))
            throw new IntrebareValidatorFailedException("Ultimul caracter din enunt nu e '?'!");
        if (enunt.length() > 100)
            throw new IntrebareValidatorFailedException("Lungimea enuntului depaseste 100 de caractere!");

    }

    public static void validateRaspuns1(String raspuns1) throws IntrebareValidatorFailedException {

        raspuns1 = raspuns1.trim();

        if (raspuns1.equals(""))
            throw new IntrebareValidatorFailedException("Raspuns1 este vid!");
        if (!String.valueOf(raspuns1.charAt(0)).equals("1") || !String.valueOf(raspuns1.charAt(1)).equals(")"))
            throw new IntrebareValidatorFailedException("Raspuns1 nu incepe cu '1)'!");
        if (raspuns1.length() > 50)
            throw new IntrebareValidatorFailedException("Lungimea raspunsului1 depaseste 50 de caractere!");
    }

    public static void validateRaspuns2(String raspuns2) throws IntrebareValidatorFailedException {

        raspuns2 = raspuns2.trim();

        if (raspuns2.equals(""))
            throw new IntrebareValidatorFailedException("Raspuns2 este vid!");
        if (!String.valueOf(raspuns2.charAt(0)).equals("2") || !String.valueOf(raspuns2.charAt(1)).equals(")"))
            throw new IntrebareValidatorFailedException("Raspuns2 nu incepe cu '2)'!");
        if (raspuns2.length() > 50)
            throw new IntrebareValidatorFailedException("Lungimea raspunsului2 depaseste 50 de caractere!");
    }

    public static void validateRaspuns3(String raspuns3) throws IntrebareValidatorFailedException {

        raspuns3 = raspuns3.trim();

        if (raspuns3.equals(""))
            throw new IntrebareValidatorFailedException("Raspuns3 este vid!");
        if (!String.valueOf(raspuns3.charAt(0)).equals("3") || !String.valueOf(raspuns3.charAt(1)).equals(")"))
            throw new IntrebareValidatorFailedException("Raspuns3 nu incepe cu '3)'!");
        if (raspuns3.length() > 50)
            throw new IntrebareValidatorFailedException("Lungimea raspunsului3 depaseste 50 de caractere!");

    }

    public static void validateRaspunsCorect(String raspunsCorect) throws IntrebareValidatorFailedException {

        raspunsCorect = raspunsCorect.trim();

        if (!raspunsCorect.equals("1") && !raspunsCorect.equals("2") && !raspunsCorect.equals("3"))
            throw new IntrebareValidatorFailedException("Raspunsul corect nu este unul dintre caracterele {'1', '2', " +
                    "'3'}");
    }

    public static void validateDomeniu(String domeniu) throws IntrebareValidatorFailedException {

        domeniu = domeniu.trim();

        if (domeniu.equals(""))
            throw new IntrebareValidatorFailedException("Domeniul este vid!");
        if (!Character.isUpperCase(domeniu.charAt(0)))
            throw new IntrebareValidatorFailedException("Prima litera din domeniu nu e majuscula!");
        if (domeniu.length() > 30)
            throw new IntrebareValidatorFailedException("Lungimea domeniului depaseste 30 de caractere!");

    }

    public void validate(Intrebare intrebare) throws IntrebareValidatorFailedException {
        validateEnunt(intrebare.getEnunt());
        validateRaspuns1(intrebare.getRaspuns1());
        validateRaspuns2(intrebare.getRaspuns2());
        validateRaspuns3(intrebare.getRaspuns3());
        validateRaspunsCorect(intrebare.getRaspunsCorect());
        validateDomeniu(intrebare.getDomeniu());
    }
}

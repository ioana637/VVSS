package ccir2082MV.evaluator.repository;

import ccir2082MV.evaluator.exception.DuplicateIntrebareException;
import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.model.Intrebare;
import ccir2082MV.evaluator.validator.IValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class IntrebariRepository implements IIntrebariRepository {

    private final IValidator<Intrebare> validator;
    private List<Intrebare> intrebari;

    public IntrebariRepository(IValidator<Intrebare> validator) {
        this.validator= validator;
        setIntrebari(new LinkedList<Intrebare>());
    }

    public void addIntrebare(Intrebare i) throws DuplicateIntrebareException, IntrebareValidatorFailedException {
        if (exists(i))
            throw new DuplicateIntrebareException("Intrebarea deja exista!");
        validator.validate(i);
        intrebari.add(i);
    }

    public boolean exists(Intrebare i) {
        for (Intrebare intrebare : intrebari)
            if (intrebare.equals(i))
                return true;
        return false;
    }

    public Intrebare pickRandomIntrebare() {
        Random random = new Random();
        return intrebari.get(random.nextInt(intrebari.size()));
    }

    public int getNumberOfDistinctDomains() {
        return getDistinctDomains().size();

    }

    public Set<String> getDistinctDomains() {
        Set<String> domains = new TreeSet<String>();
        for (Intrebare intrebre : intrebari)
            domains.add(intrebre.getDomeniu());
        return domains;
    }

    public List<Intrebare> getIntrebariByDomain(String domain) {
        List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
        for (Intrebare intrebare : intrebari) {
            if (intrebare.getDomeniu().equals(domain)) {
                intrebariByDomain.add(intrebare);
            }
        }

        return intrebariByDomain;
    }

    public int getNumberOfIntrebariByDomain(String domain) {
        return getIntrebariByDomain(domain).size();
    }

    public List<Intrebare> loadIntrebariFromFile(String f) throws IntrebareValidatorFailedException {

        List<Intrebare> intrebari = new LinkedList<Intrebare>();
        BufferedReader br = null;
        String line = null;
        List<String> intrebareAux;
        Intrebare intrebare;

        try {
            br = new BufferedReader(new FileReader(f));
            line = br.readLine();
            while (line != null) {
                intrebareAux = new LinkedList<String>();
                while (!line.equals("##")) {
                    intrebareAux.add(line);
                    line = br.readLine();
                }
                intrebare = new Intrebare(intrebareAux.get(0), intrebareAux.get(1), intrebareAux.get(2), intrebareAux
                        .get(3), intrebareAux.get(4), intrebareAux.get(5));
                validator.validate(intrebare);
                intrebari.add(intrebare);
                line = br.readLine();
            }
        } catch (IOException e) {
            // TODO: handle exception
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // TODO: handle exception
            }
        }

        return intrebari;
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

}

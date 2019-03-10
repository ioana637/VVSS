package ccir2082MV.evaluator.repository;

import ccir2082MV.evaluator.exception.DuplicateIntrebareException;
import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;
import ccir2082MV.evaluator.model.Intrebare;

import java.util.List;

public interface IIntrebariRepository {
    void addIntrebare(Intrebare i) throws DuplicateIntrebareException, IntrebareValidatorFailedException;

    List<Intrebare> getIntrebari();

    boolean exists(Intrebare i);
}

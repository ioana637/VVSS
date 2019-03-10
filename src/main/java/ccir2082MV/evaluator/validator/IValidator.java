package ccir2082MV.evaluator.validator;

import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;

public interface IValidator <E>{
    void validate(E entity) throws IntrebareValidatorFailedException;
}

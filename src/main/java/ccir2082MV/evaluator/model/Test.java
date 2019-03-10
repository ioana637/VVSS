package ccir2082MV.evaluator.model;

import java.util.LinkedList;
import java.util.List;

public class Test {

    private List<Intrebare> intrebari;

    public Test() {
        intrebari = new LinkedList<Intrebare>();
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Intrebare intr : intrebari) {
            str.append(intr.getEnunt()).append(" ").append(intr.getRaspuns1()).append(" ").append(intr.getRaspuns2()).append(" ").append(intr
                    .getRaspuns3()).append(" ").append(intr.getRaspunsCorect()).append(" ").append(intr.getDomeniu()).append("\n");
        }
        return str.toString();
    }
}

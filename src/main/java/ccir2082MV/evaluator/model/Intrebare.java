package ccir2082MV.evaluator.model;


import ccir2082MV.evaluator.exception.IntrebareValidatorFailedException;

public class Intrebare {

    private String enunt;
    private String raspuns1;
    private String raspuns2;
    private String raspuns3;
    private String raspunsCorect;
    private String domeniu;

    public Intrebare() {
    }

    public Intrebare(String enunt, String raspuns1, String raspuns2, String raspuns3,
                     String raspunsCorect, String domeniu) throws IntrebareValidatorFailedException {
        this.enunt = enunt;
        this.raspuns1 = raspuns1;
        this.raspuns2 = raspuns2;
        this.raspuns3 = raspuns3;
        this.raspunsCorect = raspunsCorect;
        this.domeniu = domeniu;
    }


    public String getEnunt() {
        return enunt;
    }

    public void setEnunt(String enunt) {
        this.enunt = enunt;
    }

    public String getRaspuns1() {
        return raspuns1;
    }

    public void setRaspuns1(String raspuns1) {
        this.raspuns1 = raspuns1;
    }

    public String getRaspuns2() {
        return raspuns2;
    }

    public void setRaspuns2(String raspuns2) {
        this.raspuns2 = raspuns2;
    }

    public String getRaspuns3() {
        return raspuns3;
    }

    public void setRaspuns3(String raspuns3) {
        this.raspuns3 = raspuns3;
    }

    public String getRaspunsCorect() {
        return raspunsCorect;
    }

    public void setRaspunsCorect(String raspunsCorect) {
        this.raspunsCorect = raspunsCorect;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intrebare) {
            Intrebare i = (Intrebare) obj;
            if (this.enunt.equals(i.getEnunt()) &&
                    this.raspuns1.equals(i.getRaspuns1()) &&
                    this.raspuns2.equals(i.getRaspuns2()) &&
                    this.raspuns3.equals(i.getRaspuns3()) &&
                    this.raspunsCorect.equals(i.getRaspunsCorect()) &&
                    this.domeniu.equals(i.getDomeniu()))
                return true;
        }
        return false;
    }

}

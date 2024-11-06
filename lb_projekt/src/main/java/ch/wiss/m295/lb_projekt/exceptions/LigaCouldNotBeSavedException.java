package ch.wiss.m295.lb_projekt.exceptions;

public class LigaCouldNotBeSavedException extends RuntimeException {

    public LigaCouldNotBeSavedException(String ligaName) {
        super("Die Liga mit dem Namen" + ligaName + "konnte nicht gespeichert werden.");
    }

}

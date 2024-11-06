package ch.wiss.m295.lb_projekt.exceptions;

public class LigenLoadException extends RuntimeException {

    public LigenLoadException() {
        super("Die Ligen konnten nicht geladen werden.");
    }

}

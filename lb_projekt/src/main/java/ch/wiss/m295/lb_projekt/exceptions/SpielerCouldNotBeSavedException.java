package ch.wiss.m295.lb_projekt.exceptions;

public class SpielerCouldNotBeSavedException extends RuntimeException {
    public SpielerCouldNotBeSavedException(String spielerName) {
        super("Der Spieler mit dem Namen" + spielerName + "konnte nicht gespeichert werden.");
    }
}

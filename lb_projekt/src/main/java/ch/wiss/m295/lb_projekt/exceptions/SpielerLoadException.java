package ch.wiss.m295.lb_projekt.exceptions;

public class SpielerLoadException extends RuntimeException {
    public SpielerLoadException() {
        super("Die Spieler konnten nicht geladen werden.");
    }

}

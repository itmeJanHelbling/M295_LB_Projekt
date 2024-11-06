package ch.wiss.m295.lb_projekt.exceptions;

public class TeamLoadException extends RuntimeException {
    public TeamLoadException() {
        super("Die Teams konnten nicht geladen werden.");
    }
}

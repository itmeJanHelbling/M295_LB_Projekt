package ch.wiss.m295.lb_projekt.exceptions;

public class TeamCouldNotBeSavedException extends RuntimeException {
    public TeamCouldNotBeSavedException(String teamName) {
        super("Das Team mit dem Namen" + teamName + "konnte nicht gespeichert werden.");
    }

}

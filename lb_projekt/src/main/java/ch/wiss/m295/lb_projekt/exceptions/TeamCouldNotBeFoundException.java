package ch.wiss.m295.lb_projekt.exceptions;

public class TeamCouldNotBeFoundException extends RuntimeException {
    public TeamCouldNotBeFoundException(long teamId) {
        super("Das Team mit der Id: " + teamId + " konnte nicht gefunden werden.");
    }
}

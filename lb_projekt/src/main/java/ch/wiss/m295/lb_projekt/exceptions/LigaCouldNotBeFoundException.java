package ch.wiss.m295.lb_projekt.exceptions;

public class LigaCouldNotBeFoundException extends RuntimeException {
    public LigaCouldNotBeFoundException(long ligaId) {
        super("Die Liga mit der Id: " + ligaId + " konnte nicht gefunden werden.");
    }
}
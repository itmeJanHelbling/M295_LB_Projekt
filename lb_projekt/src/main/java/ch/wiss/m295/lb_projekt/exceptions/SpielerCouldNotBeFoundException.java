package ch.wiss.m295.lb_projekt.exceptions;

public class SpielerCouldNotBeFoundException extends RuntimeException {
    public SpielerCouldNotBeFoundException(long spielerId) {
        super("Der Spieler mit der Id: " + spielerId + " konnte nicht gefunden werden.");
    }

}

package io.reactivestax.problems.trading.customexceptions;

public class HitErrorsThresholdException extends  RuntimeException {
    public HitErrorsThresholdException(String message) {
        super(message);
    }
}

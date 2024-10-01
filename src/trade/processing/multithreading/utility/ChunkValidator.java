package trade.processing.multithreading.utility;

public interface ChunkValidator {
    boolean quickValidator (String[] payload);
    String fieldsValidator(String[] payload);
}

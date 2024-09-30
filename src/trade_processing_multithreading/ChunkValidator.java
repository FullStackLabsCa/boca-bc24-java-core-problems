package trade_processing_multithreading;

public interface ChunkValidator {
    boolean quickValidator (String[] payload);
    String fieldsValidator(String[] payload);
}

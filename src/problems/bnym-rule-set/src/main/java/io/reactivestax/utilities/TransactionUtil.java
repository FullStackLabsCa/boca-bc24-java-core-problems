package io.reactivestax.utilities;

public interface TransactionUtil {
    void startTransaction();

    void commitTransaction();

    void rollbackTransaction();
}

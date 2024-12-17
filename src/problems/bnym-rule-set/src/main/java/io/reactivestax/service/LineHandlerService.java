package io.reactivestax.service;

public interface LineHandlerService {

    void ruleSetIdentifier(String line);

    void accountNumberSetValuesConcentrationLimit(String line);

    void eligibilityGroup(String line);

    void eligibilityRule(String line);

    void margins(String line);
}

package io.reactivestax.service;

public interface LineHandlerService {

    void ruleSetIdentifier(String key, String value);

    void accountNumberSetValuesConcentrationLimit(String key, String value);

    void eligibilityGroup(String key, String value);

    void eligibilityRule(String key, String value);

    void margins(String key, String value);
}

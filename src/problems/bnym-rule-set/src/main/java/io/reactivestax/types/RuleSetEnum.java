package io.reactivestax.types;

public enum RuleSetEnum {
    RULE_01("01"),
    RULE_02("02"),
    RULE_03("03"),
    RULE_04("04"),
    RULE_05("05"),
    RULE_06("06"),
    RULE_07("07");

    private final String code;

    RuleSetEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RuleSetEnum fromCode(String code) {
        for (RuleSetEnum rule : RuleSetEnum.values()) {
            if (rule.getCode().equals(code)) {
                return rule;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

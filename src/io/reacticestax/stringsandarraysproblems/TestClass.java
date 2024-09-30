package io.reacticestax.stringsandarraysproblems;

import org.junit.Test;

public class TestClass {
    @Test
    public void testStringSplit(){
        String expression = "55/210+5-433*31";
        final String afterReplacementString = expression.replaceAll("[^0-9]", "=");
        System.out.println("afterReplacementString = " + afterReplacementString);
        final String[] splitStringArray = afterReplacementString.split("[=]");
        for (String string: splitStringArray){
            System.out.println("string = " + string);
        }
    }
}

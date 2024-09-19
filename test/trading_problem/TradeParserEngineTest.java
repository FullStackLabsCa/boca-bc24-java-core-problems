package trading_problem;

import org.junit.Test;
import trading_parser.model.Trade;
import trading_parser.service.TradeParserEngine;
import trading_parser.utility.InvalidThresholdValueException;

import static org.junit.Assert.assertEquals;

public class TradeParserEngineTest {


    @Test (expected = InvalidThresholdValueException.class)
    public void testNullThreshold(){
        TradeParserEngine.validateThresholdInput(null);
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testNegativeThreshold(){
        TradeParserEngine.validateThresholdInput("-34.5");
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testNonNumericThreshold(){
        TradeParserEngine.validateThresholdInput("sdfas");
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testOverThreshold(){
        TradeParserEngine.validateThresholdInput("111");
    }

    @Test
    public void testCorrectThreshold(){
        assertEquals(34.5, TradeParserEngine.validateThresholdInput("34.5"), 0.001);
        assertEquals(100, TradeParserEngine.validateThresholdInput("100"), 0.001);
        assertEquals(1, TradeParserEngine.validateThresholdInput("1"),0.001);
    }

    @Test
    public void testReadingAllWrongValues(){

    }

    @Test
    public void testReadingAllGoodValues(){

    }

    @Test
    public void testReadingEqualToThresholdValues(){

    }

    @Test
    public void testReadingGoodMoreThanThresholdValues(){

    }

    @Test
    public void testWritingAllNonInsertable(){

    }

    @Test
    public void testWritingAllInsertable(){

    }

    @Test
    public void testWritingEqualToThreshold(){

    }

    @Test
    public void testWritingGoodMoreThanThresholdValues(){

    }


}

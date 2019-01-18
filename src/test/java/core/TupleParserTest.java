package core;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static core.TupleParser.parseInParams;
import static org.junit.Assert.assertEquals;

public class TupleParserTest {

    @Test
    public void testParserOnEmpty() {
        var testedString = List.of(
            "",
            "    ",
            "<>",
            "  <  > ",
            "  <  >",
            "<  > "
        );

        var count = testedString
                .stream()
                .mapToLong(str -> parseInParams(str).size())
                .sum();

        assertEquals(count, 0);
    }

    @Test
    public void testParserOnEmptyError() {
        var testedString = List.of(
                "<",
                ">",
                "<   ",
                "  <   ",
                "   >",
                "<<  > ",
                "<  >> "
        );

        var errorCount = 0;

        for (String str : testedString) {
            try {
                parseInParams(str);
            } catch (IllegalStateException e) {
                errorCount++;
            }
        }

        assertEquals(errorCount, testedString.size());
    }

    /**
     * digit only
     */
    @Test
    public void testParserOnCorrectDigit() {
        var testedString = List.of(
                "<1234>",
                "  <34>",
                "  < 34 > ",
                "  <  34 ,  456>",
                "  < 35,    56,   67  ,  23, 6>"
        );

        var result = testedString
                .stream()
                .flatMap(str -> parseInParams(str).stream())
                .collect(Collectors.toList());

        assertEquals(result.size(), 10);

    }

    /**
     * String only
     */
    @Test
    public void testParserOnCorrectString() {
        var testedString = List.of(
                "<\"test0\">",
                "<\"test0\", \"test1\">",
                "<   \"test0\",      \"test1\"    ,    \"test1\" ,   \"test1\">"
        );

        var result = testedString
                .stream()
                .flatMap(str -> parseInParams(str).stream())
                .collect(Collectors.toList());

        assertEquals(result.size(), 7);

    }

    @Test
    public void testParserOnCorrectMixed() {
        var testedString = List.of(
                "<\"test0\", 8>",
                "<\"test0\",   4567,  \"test1\">",
                "<   \"test0\",    5668, 4  \"test1\"    ,    \"test1\" ,  56, \"test1\" , 78 ,4>"
        );

        var result = testedString
                .stream()
                .flatMap(str -> parseInParams(str).stream())
                .collect(Collectors.toList());

        assertEquals(result.size(), 14);

    }


}

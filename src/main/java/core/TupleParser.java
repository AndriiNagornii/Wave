package core;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TupleParser {

    private static final int TUPLE_OPEN = 0;
    private static final int TUPLE_CLOSE = 1;

    private static char[] chars;
    private static int i = 0;


    public static List<Object> parseInParams(String tupleAsString) {
        chars = tupleAsString.toCharArray();
        i = 0;

        var paramsAsObj = new LinkedList<>();

        var currentState = TUPLE_CLOSE; // 0 is initial state

        while (hasNext()) {
            char ch = get();
            if (ch == ' ') {
                next();
                continue;
            }

            if (ch == '<') {
                if (currentState == TUPLE_CLOSE) {
                    currentState = TUPLE_OPEN;
                    next();
                    continue;
                }
                else
                    throw new IllegalStateException();
            }

            if (isDigit(ch)) {
                if (currentState == TUPLE_OPEN) {
                    paramsAsObj.add(readDigit());
                    continue;
                }
                else
                    throw new IllegalStateException();

            }

            if (ch == '"') {
                if (currentState == TUPLE_OPEN) {
                    paramsAsObj.add(readString());
                    continue;
                }
                else
                    throw new IllegalStateException();

            }

            if (ch == ',') {
                if (currentState == TUPLE_OPEN) {
                    processSeparator();
                    continue;
                }
                else
                    throw new IllegalStateException();

            }

            if (ch == '>') {
                if (currentState == TUPLE_OPEN) {
                    currentState = TUPLE_CLOSE;
                    next();
                    continue;
                }
                else
                    throw new IllegalStateException();

            }
        }

        if (currentState != TUPLE_CLOSE)
            throw new IllegalStateException();

        return paramsAsObj;
    }

    private static void processSeparator() {
        next();
        while (hasNext()) {
            var ch = get();
            if (ch == ' ') {
                next();
            } else if (isDigit(ch) || ch == '\"') {
                return;
            } else {
                throw new IllegalStateException();
            }

        }
    }

    private static String readDigit() {
        var digit = new LinkedList<Character>();
        while (hasNext()) {
            var ch = get();
            if (isDigit(ch)) {
                digit.add(ch);
                next();
            } else {
                return getString(digit);
            }
        }
        return null;
    }

    private static String readString() {
        next();
        var str = new LinkedList<Character>();
        while (hasNext()) {
            var ch = get();
            if (ch == '"') {
                next();
                return getString(str);
            }
            str.add(ch);
            next();
        }
        return null;
    }

    private static String getString(LinkedList<Character> chars) {
        var arr = new char[chars.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = chars.get(i);
        }
        return String.valueOf(arr);
    }

    private static char get() {
        return chars[i];
    }

    private static void next() {
        i++;
    }

    private static boolean hasNext() {
        return i < chars.length;
    }

    private static boolean isDigit(char ch) {
        switch (ch) {
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            default:
                return false;
        }
    }


}

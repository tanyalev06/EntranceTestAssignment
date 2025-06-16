public class ArithmeticExpressionEvaluator {

    private String expression;
    private int pos = -1;
    private int ch;

    public double evaluate(String expr) {
        this.expression = expr.replaceAll("\\s+", "");
        this.pos = -1;
        nextChar();
        double result = parseExpression();
        if (pos < expression.length()) {
            throw new RuntimeException("Лишние символы в выражении");
        }
        return result;
    }

    private void nextChar() {
        pos++;
        ch = (pos < expression.length()) ? expression.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parseExpression() {
        double x = parseTerm();
        while (true) {
            if (eat('+')) x += parseTerm();
            else if (eat('-')) x -= parseTerm();
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        while (true) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) {
                double divisor = parseFactor();
                if (divisor == 0) throw new ArithmeticException("деление на ноль");
                x /= divisor;
            } else return x;
        }
    }

    private double parseFactor() {
        if (eat('+')) return parseFactor();
        if (eat('-')) return -parseFactor();

        double x;
        int startPos = pos;

        if (eat('(')) {
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Пропущена закрывающая скобка");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            String numberStr = expression.substring(startPos, pos);
            x = Double.parseDouble(numberStr);
        } else {
            throw new RuntimeException("Ожидалось число или скобка");
        }

        return x;
    }
}
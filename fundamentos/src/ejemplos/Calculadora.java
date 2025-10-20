package ejemplos;

public class Calculadora {

    public static double calcular(double num1, double num2, String operacion) {
        switch (operacion) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        double num1 = 10, num2 = 2;
        System.out.println("suma: " + Calculadora.calcular(num1, num2, "+"));
        System.out.println("resta: " + Calculadora.calcular(num1, num2, "-"));
        System.out.println("multiplicación: " + Calculadora.calcular(num1, num2, "*"));
        System.out.println("division: " + Calculadora.calcular(num1, num2, "/"));
        System.out.println("raiz cuadrada: " + Calculadora.calcular(num1, num2, "*/"));
    }
}

package variables;

import clases.Persona;

import java.util.List;

public class VariableMain {
    public static void main(String[] args) {
        int num1 = 30;
        int num2 = 20;

        double suma = VariableMain.calcular(num1, num2, "+");
        double resta = VariableMain.calcular(num1, num2, "-");
        double multplicacion = VariableMain.calcular(num1, num2, "*");
        double divison = VariableMain.calcular(num1, num2, "/");
        imprimirResultado(divison);
       /* System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);
        System.out.println("Multiplicacion: " + multplicacion);
        System.out.println("Division: " + divison);*/

        List<String> frutas = List.of("MAZANA", "PERA", "SANDIA");

        System.out.println("Bucle foreach");
        for (String fruta : frutas) {
            System.out.println("Fruta : " + fruta);
        }

        System.out.println("Bucle for");
        for (int i = 0; i < frutas.size(); i++) {
            System.out.println("Fruta: " + frutas.get(i));
        }
        System.out.println("Bucle while");
        int i = 0;
        while (i < frutas.size()) {
            System.out.println("Fruta: " + frutas.get(i));
            i++;
        }


        Persona persona = new Persona();
        persona.setNombre("LUIS SUAREZ");
      //  persona.imprimirNombre();
    }

    public static double calcular(int num1, int num2, String operacion) {

        switch (operacion) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }

        return 0;
    }

    public static void imprimirResultado(double resultado) {
        System.out.println("Resultado: " + resultado);
    }


}

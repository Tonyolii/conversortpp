package convertert.conversortapp.service;

import java.util.Scanner;

public class TemperaturaService {

    public static double converter(String de, String para, double valor) {
        if (de.equalsIgnoreCase("celcius") && para.equalsIgnoreCase("fahrenheit")){
            return (valor * 9/ 5) + 32;
        }
        else if (de.equalsIgnoreCase("fahrenheit") && para.equalsIgnoreCase("celcius")){
            return (valor -32) * 5 /9;
        }
        else if (de.equalsIgnoreCase("celcius") && para.equalsIgnoreCase("kelvin")){
            return (valor + 273.25);
        }
        else if (de.equalsIgnoreCase("kelvin") && para.equalsIgnoreCase("celcius")){
            return (valor - 273.15);
        }
        else {
            throw new IllegalArgumentException("Conversão não suportada.");
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a unidade que deseja converter (Celcius, Kelvin, Fahrenheit): ");
        String de = scanner.nextLine();

        System.out.println("Digite para qual unidade quer converter (Celcius, Kelvin, Fahrenheit): ");
        String para = scanner.nextLine();

        System.out.println("Digite o valor da temperatura: ");
        double valor = scanner.nextDouble();

        try {
            double resultado = converter(de, para, valor);

            System.out.println("Resultado da conversão: " + resultado + "" + para);
        } catch(IllegalArgumentException e){
            System.out.println ("Erro: " + e.getMessage());
        }
        scanner.close();
    }
}

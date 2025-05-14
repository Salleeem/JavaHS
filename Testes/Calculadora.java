package Testes;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecione uma operação:\n" +
                "1 - Soma\n" +
                "2 - Subtração\n" +
                "3 - Multiplicação\n" +
                "4 - Divisão\n" +
                "5 - Porcentagem\n" +
                "6 - Potência\n" +
                "7 - Radiciação\n" +
                "8 - Logaritmação\n" +
                "9 - Equação de 1° grau\n" +
                "10 - Equação de 2° grau\n");

        String operacao = sc.nextLine();
        double a;
        double b;
        double c;

        switch (operacao) {
            case "1":

                // Operação de soma.

                System.out.println("Digite o primeiro número da soma:");
                a = sc.nextDouble();
                System.out.println("Digite o segundo número da soma:");
                b = sc.nextDouble();
                System.out.printf("O resultado dessa soma é %.2f\n", a + b);

                break;

            case "2":

                // Operação de subtração.

                System.out.println("Digite o número a ser subtraído:");
                a = sc.nextDouble();
                System.out.println("Digite o valor a subtrair:");
                b = sc.nextDouble();
                System.out.printf("O resultado dessa subtração é %.2f\n", a - b);

                break;

            case "3":

                // Operação de multiplicação.

                System.out.println("Digite o primeiro número da multiplicação:");
                a = sc.nextDouble();
                System.out.println("Digite o segundo número da multiplicação:");
                b = sc.nextDouble();
                System.out.printf("O resultado dessa multiplicação é %.2f\n", a * b);

                break;

            case "4":

                // Operação de divisão.

                System.out.println("Digite o dividendo:");
                a = sc.nextDouble();
                System.out.println("Digite o divisor:");
                b = sc.nextDouble();
                if (b != 0) {
                    System.out.printf("O resultado dessa divisão é %.2f\n", a / b);
                } else {
                    System.out.println("Não é possível dividir por zero.");
                }

                break;

            case "5":

                // Operação de porcentagem.

                System.out.println("Digite a porcentagem:");
                a = sc.nextDouble();
                System.out.println("Digite o número base:");
                b = sc.nextDouble();
                System.out.printf("%.2f%% de %.2f é %.2f\n", a, b, (a / 100) * b);

                break;

            case "6":

                // Operação de potência.

                System.out.println("Digite o número a ser potenciado");
                a = sc.nextDouble();
                System.out.println("Digite a potência desejada");
                b = sc.nextDouble();
                System.out.printf("%.2f elevado a %.2f é %.2f\n", a, b, (Math.pow(a, b)));

                break;

            case "7":

                // Operação de raiz quadrada.

                System.out.println("Digite um número para ser tirado a raiz");
                a = sc.nextDouble();
                System.out.printf("A raiz quadrada de %.2f é %.2f\n", a, (Math.sqrt(a)));

                break;

            case "8":

                // Operação com Log.

                System.out.println();

            case "9":

                // Operação de equação de 1° grau(ax + b = c).

                System.out.println("Operação no formato (ax + b = c)");
                System.out.println("Digite o valor de a");
                a = sc.nextDouble();
                System.out.println("Digite o valor de b");
                b = sc.nextDouble();
                System.out.println("Digite o valor de c");
                c = sc.nextDouble();

                if (a != 0) {
                    double x = (c - b) / a;
                    System.out.printf("A solução da equação é x = %.2f", x);

                } else if (b == c) {
                    System.out.println("A equação tem infinitas soluções");
                } else {
                    System.out.println("A equação não tem solução");
                }

                break;

            case "10":

                // Equação de 2° grau(ax² + bx + c = 0 )

                System.out.println("Operação no formato (ax² + b + c = 0)");
                System.out.println("Digite o valor de a");
                a = sc.nextDouble();
                System.out.println("Digite o valor de b");
                b = sc.nextDouble();
                System.out.println("Digite o valor de c");
                c = sc.nextDouble();

                if (a == 0) {
                    System.out.println("Isso não é uma equação do 2º grau (a não pode ser 0).");
                } else {
                    double delta = Math.pow(b, 2) - 4 * a * c;
            
                    if (delta > 0) {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        System.out.printf("A equação tem duas raízes reais: x1 = %.2f e x2 = %.2f\n", x1, x2);
                    } else if (delta == 0) {
                        double x = -b / (2 * a);
                        System.out.printf("A equação tem uma raiz real dupla: x = %.2f\n", x);
                    } else {
                        System.out.println("A equação não possui raízes reais.");
                    }
                }

                break;
            default:

                System.out.println("Operação inválida.");
        }

        sc.close();
    }
}

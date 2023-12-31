import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\nEscolha um exercício para executar:");
            System.out.print("|1. Endorrelação");
            System.out.print("|2. Fatorial");
            System.out.print("|3. Fecho transitivo");
            System.out.print("|4. Fibonacci");
            System.out.print("|5. Permutações");
            System.out.print("|6. Conjunto");
            System.out.print("|7. Lógicas");
            System.out.print("|8. Primos");
            System.out.print("|9. Função");
            System.out.print("|10. Paridade");
            System.out.println("|0. Sair|");
            System.out.print("-> ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Ex1();
                    break;
                case 2:
                    Ex2(args, scanner);
                    break;
                case 3:
                    Ex3(args);
                    break;
                case 4:
                    Ex4(args, scanner);
                    break;
                case 5:
                    Ex5(args);
                    break;
                case 6:
                    Ex6(args);
                    break;
                case 7:
                    Ex7(args);
                    break;
                case 8:
                    Ex8(args);
                    break;
                case 9:
                    Ex9(args);
                    break;
                case 10:
                    Ex10(args);
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
            }
        }

    }

    // Endorrelação
    public static void Ex1() {
        Set<List<Integer>> relacao = new HashSet<>();
        relacao.add(Arrays.asList(1, 1));
        relacao.add(Arrays.asList(2, 2));
        relacao.add(Arrays.asList(3, 3));
        relacao.add(Arrays.asList(1, 2));
        relacao.add(Arrays.asList(2, 1));
        relacao.add(Arrays.asList(2, 3));
        relacao.add(Arrays.asList(3, 2));

        verificaPropriedades(relacao);
    }

    public static void verificaPropriedades(Set<List<Integer>> relacao) {
        boolean simetrica = true;
        boolean antiSimetrica = true;
        boolean reflexiva = true;
        boolean irreflexiva = true;
        boolean transitiva = true;

        for (List<Integer> par : relacao) {
            int a = par.get(0);
            int b = par.get(1);

            if (a != b && !relacao.contains(Arrays.asList(b, a))) {
                simetrica = false;
            }

            if (a != b && relacao.contains(Arrays.asList(b, a))) {
                antiSimetrica = false;
            }
        }

        for (int i = 1; i <= relacao.size(); i++) {
            if (!relacao.contains(Arrays.asList(i, i))) {
                reflexiva = false;
            }

            if (relacao.contains(Arrays.asList(i, i))) {
                irreflexiva = false;
            }
        }

        for (List<Integer> par1 : relacao) {
            int a = par1.get(0);
            int b = par1.get(1);

            for (List<Integer> par2 : relacao) {
                int c = par2.get(0);
                int d = par2.get(1);

                if (b == c && !relacao.contains(Arrays.asList(a, d))) {
                    transitiva = false;
                }
            }
        }

        System.out.println("Simétrica: " + simetrica);
        System.out.println("Antissimétrica: " + antiSimetrica);
        System.out.println("Reflexiva: " + reflexiva);
        System.out.println("Irreflexiva: " + irreflexiva);
        System.out.println("Transitiva: " + transitiva);
    }

    // Fatorial
    public static void Ex2(String[] args, Scanner scanner) {
        System.out.println("Digite um número para calcular o fatorial:");
        int numero = scanner.nextInt();

        long fatorial = calcularFatorial(numero);
        System.out.println("O fatorial de " + numero + " é: " + fatorial);
    }

    public static long calcularFatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            long resultado = 1;
            for (int i = 2; i <= n; i++) {
                resultado *= i;
            }
            return resultado;
        }
    }

    // Fecho transitivo
    public static void Ex3(String[] args) {
        int[][] matrizAdjacencia = {
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 1 },
                { 1, 0, 0, 0 }
        };

        calcularFechoTransitivo(matrizAdjacencia);
    }

    public static void calcularFechoTransitivo(int[][] matriz) {
        int tamanho = matriz.length;

        int[][] fechoTransitivo = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                fechoTransitivo[i][j] = matriz[i][j];
            }
        }

        for (int k = 0; k < tamanho; k++) {
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    fechoTransitivo[i][j] = fechoTransitivo[i][j] | (fechoTransitivo[i][k] & fechoTransitivo[k][j]);
                }
            }
        }

        System.out.println("Fecho Transitivo:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(fechoTransitivo[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Fibonacci
    public static void Ex4(String[] args, Scanner scanner) {
        System.out.println("Digite a quantidade de termos da sequência Fibonacci:");
        int quantidadeTermos = scanner.nextInt();

        System.out.println("Sequência Fibonacci até o " + quantidadeTermos + "º termo:");
        for (int i = 0; i < quantidadeTermos; i++) {
            System.out.print(calcularFibonacci(i) + " ");
        }
    }

    public static int calcularFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            int anterior = 0;
            int atual = 1;
            int resultado = 0;

            for (int i = 2; i <= n; i++) {
                resultado = anterior + atual;
                anterior = atual;
                atual = resultado;
            }
            return resultado;
        }
    }

    // Permutações
    public static void Ex5(String[] args) {
        List<Integer> elementos = Arrays.asList(1, 2, 3);
        List<List<Integer>> permutacoes = gerarPermutacoes(elementos);

        System.out.println("Todas as permutações:");
        for (List<Integer> permutacao : permutacoes) {
            System.out.println(permutacao);
        }
    }

    public static List<List<Integer>> gerarPermutacoes(List<Integer> elementos) {
        List<List<Integer>> result = new ArrayList<>();
        gerar(elementos, 0, result);
        return result;
    }

    private static void gerar(List<Integer> elementos, int indice, List<List<Integer>> result) {
        if (indice >= elementos.size()) {
            result.add(new ArrayList<>(elementos));
            return;
        }

        for (int i = indice; i < elementos.size(); i++) {
            Collections.swap(elementos, i, indice);
            gerar(elementos, indice + 1, result);
            Collections.swap(elementos, i, indice);
        }
    }

    public static void Ex6(String[] args) {
        Set<Integer> conjuntoA = new HashSet<>();
        Set<Integer> conjuntoB = new HashSet<>();

        conjuntoA.add(1);
        conjuntoA.add(2);
        conjuntoA.add(3);
        conjuntoA.add(4);

        conjuntoB.add(3);
        conjuntoB.add(4);
        conjuntoB.add(5);
        conjuntoB.add(6);

        System.out.println("Conjunto A: " + conjuntoA);
        System.out.println("Conjunto B: " + conjuntoB);

        Set<Integer> uniao = new HashSet<>(conjuntoA);
        uniao.addAll(conjuntoB);
        System.out.println("União: " + uniao);

        Set<Integer> intersecao = new HashSet<>(conjuntoA);
        intersecao.retainAll(conjuntoB);
        System.out.println("Interseção: " + intersecao);

        Set<Integer> diferenca = new HashSet<>(conjuntoA);
        diferenca.removeAll(conjuntoB);
        System.out.println("Diferença A - B: " + diferenca);

        boolean subconjunto = conjuntoA.containsAll(conjuntoB);
        System.out.println("Conjunto B é subconjunto de A: " + subconjunto);
    }

    public static void Ex7(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor de verdade para P (true/false): ");
        boolean p;
        while (true) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("true")) {
                p = true;
                break;
            } else if (input.equalsIgnoreCase("false")) {
                p = false;
                break;
            } else {
                System.out.println("Entrada inválida. Digite 'true' ou 'false'.");
            }
        }

        System.out.print("Digite o valor de verdade para Q (true/false): ");
        boolean q;
        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("true")) {
                q = true;
                break;
            } else if (input.equalsIgnoreCase("false")) {
                q = false;
                break;
            } else {
                System.out.println("Entrada inválida. Digite 'true' ou 'false'.");
            }
        }

        int escolha;
        while (true) {
            System.out.println("Escolha a operação lógica:");
            System.out.println("1. Negacão (~)");
            System.out.println("2. Conjunção (˄)");
            System.out.println("3. Disjunção (˅)");
            System.out.println("4. Condicional (→)");
            System.out.println("5. Bicondicional (↔)");

            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                if (escolha >= 1 && escolha <= 5) {
                    break;
                } else {
                    System.out.println("Escolha inválida. Digite um número de 1 a 5.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número de 1 a 5.");
                scanner.next();
            }
        }

        switch (escolha) {
            case 1:
                System.out.println("Negação de P: " + !p);
                System.out.println("Negação de Q: " + !q);
                break;
            case 2:
                System.out.println("Conjunção de P e Q: " + (p && q));
                break;
            case 3:
                System.out.println("Disjunção de P e Q: " + (p || q));
                break;
            case 4:
                System.out.println("Condicional de P para Q: " + (!p || q));
                break;
            case 5:
                System.out.println("Bicondicional de P para Q: " + (p == q));
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public static void Ex8(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro positivo: ");
        int numero = scanner.nextInt();

        if (verificarPrimo(numero)) {
            System.out.println(numero + " é um número primo.");
        } else {
            System.out.println(numero + " não é um número primo.");
        }
    }

    public static boolean verificarPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void Ex9(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            System.out.print("Digite um valor para x: ");
            double x = scanner.nextDouble();
    
            double resultado = calcularFuncao(x);
            System.out.println("O resultado da função f(x) = x^2 + 3x - 7 é: " + resultado);
        }
    
        public static double calcularFuncao(double x) {
            return Math.pow(x, 2) + (3 * x) - 7;
        }

    public static void Ex10(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro para verificar a paridade: ");
        int numero = scanner.nextInt();

        if (verificarParidade(numero)) {
            System.out.println("O número " + numero + " é par.");
        } else {
            System.out.println("O número " + numero + " é ímpar.");
        }
    }

    public static boolean verificarParidade(int numero) {
        return numero % 2 == 0;
    }

}

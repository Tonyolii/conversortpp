package convertert.conversortapp;

import convertert.conversortapp.service.MoedaService;
import convertert.conversortapp.service.TemperaturaService;
import convertert.conversortapp.service.TrabalhistaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ConversortappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversortappApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		boolean continuar = true;

		while (continuar) {
			System.out.println("==== Conversor ====");
			System.out.println("1. Conversão de Moedas");
			System.out.println("2. Conversão de temperaturas");
			System.out.println("3. Cáclulos Trabalhistas");
			System.out.println("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					executarConversaoMoeda(scanner);
					break;
				case 2:
					executarConversaoTemperatura(scanner);
					// Chamar TemperaturaService
					break;
				case 3:
					executarCalculosTrabalhistas(scanner);// Chamar TrabalhistaService
					break;
				case 0:
					continuar = false;
					System.out.println("Saindo... Obrigado por usar o Conversor T.");
				default:
					System.out.println("Opção inválida");
			}
		}
		scanner.close();
	}

	// ====  ESTE É O CASE 1 ==========

	private static void executarConversaoMoeda(Scanner scanner) {
		System.out.println("Digite a moeda de origem (Ex.: 'USD', 'BRL', 'EUR')");
		String de = scanner.nextLine();

		System.out.println("Digite a moeda de destino (Ex.: 'USD', 'BRL', 'EUR')");
		String para = scanner.nextLine();

		System.out.println("Digite o valor a ser convertido (Use apenas numeros, sem pontos ou visgulas) ");
		double valor = scanner.nextDouble();
		scanner.nextLine();

		try {
			double resultado = MoedaService.converterMoeda(de, para, valor);
			System.out.printf("Valor convertido: %.2f %s%n", resultado, para.toUpperCase());
		} catch (Exception e) {
			System.out.println("Erro ao converter moeda" + e.getMessage());
		}
	}

	// ====  AQUI ACABA O CASE 1 ==========
	// ====  ESTE É O CASE 2     ==========

	public static void executarConversaoTemperatura(Scanner scanner) {
		System.out.println("==== Conversor de Temperatura ====");
		System.out.println("Digite a unidade de origem (Ex.: celcius, kelvin, fahrenheit): ");
		String de = scanner.nextLine();

		System.out.println("Digite a unidade de destino (Ex.: celcius, kelvin, fahrenheit): ");
		String para = scanner.nextLine();

		System.out.print("Digite o valor a ser convertido: ");
		double valor = scanner.nextDouble();
		scanner.nextLine(); //Consumir a quebra de linha (?)

		try {
			double resultado = TemperaturaService.converter(de, para, valor);
			System.out.printf("Temperatura convertida %.2f %s%n", resultado, para.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// ====  AQUI ACABA O CASE 2 ==========
	// ====  ESTE É O CASE 3     ==========

	public static void executarCalculosTrabalhistas(Scanner scanner) {
		System.out.println("==== Cálculo de Rescisão ====");
		System.out.println("Tipo de rescisão: ");
		System.out.println("1. Rescisão sem Justa Causa");
		System.out.println("2. Rescisão com Justa Causa");
		System.out.println("3. Encerramento de contrato de Experiência");
		System.out.println("4. Pedido de demissão");
		System.out.println("Escolha uma das opções: ");
		int tipo = scanner.nextInt();  // Esse tipo é um int
		scanner.nextLine();  // Consumir o '\n'

		System.out.println("Digite seu último salário (apenas números, sem pontos nem vírgulas.) ");
		double salario = scanner.nextDouble();

		System.out.println("Digite os dias trabalhados no mês atual: ");
		int diasTrabalhados = scanner.nextInt();

		System.out.println("Digite os meses trabalhados neste ano: ");
		int mesesTrabalhados = scanner.nextInt();

		System.out.println("Possui férias vencidas? (s/n): ");
		boolean feriasVencidas = scanner.nextLine().trim().equalsIgnoreCase("s");

		System.out.println("==== Cálculo ====");

		double saldoSalario = TrabalhistaService.calcularSaldoSalarial(salario, diasTrabalhados);
		double feriasProporcionais = TrabalhistaService.calcularFeriasProporcionais(salario, mesesTrabalhados);
		double tercoSobreFerias = TrabalhistaService.calcularTercoFerias(feriasProporcionais);
		double decimoTerceiro = TrabalhistaService.calcularDecimoTerceiro(salario, mesesTrabalhados);

		double avisoPrevio = 0;
		double multaFgts = 0;
		double feriasVencidasValor = 0;
		double tercoFeriasVencidas = 0;

		if (feriasVencidas) {
			feriasVencidasValor = salario;
			tercoFeriasVencidas = TrabalhistaService.calcularTercoFerias(salario);
		}

		// Aqui você converte o `tipo` para o tipo de rescisão em String
		String tipoRescisao = "";
		switch (tipo) {
			case 1: // Sem justa causa
				tipoRescisao = "sem_justa_causa";
				break;
			case 2: // Com justa causa
				tipoRescisao = "com_justa_causa";
				break;
			case 3: // Encerramento de experiência
				tipoRescisao = "encerramento_experiencia";
				break;
			case 4: // Pedido de demissão
				tipoRescisao = "pedido_demissao";
				break;
			default:
				System.out.println("Opção inválida!");
				return;
		}

		// Agora você pode passar a String `tipoRescisao` para os métodos
		avisoPrevio = TrabalhistaService.calcularAvisoPrevio(salario, tipoRescisao);
		multaFgts = TrabalhistaService.calcularMultaFgts(salario, tipoRescisao);

		// Calcula o total
		double total = saldoSalario + feriasProporcionais + tercoSobreFerias + decimoTerceiro + avisoPrevio + multaFgts + feriasVencidasValor + tercoFeriasVencidas;

		System.out.printf("Saldo de salário: R$ %.2f%n", saldoSalario);
		System.out.printf("Férias proporcionais: R$ %.2f%n", feriasProporcionais);
		System.out.printf("1/3 sobre férias proporcionais: R$ %.2f%n", tercoSobreFerias);

		if (feriasVencidas) {
			System.out.printf("Férias vencidas: R$ %.2f%n", feriasVencidasValor);
			System.out.printf("1/3 sobre férias vencidas: R$ %.2f%n", tercoFeriasVencidas);
		}
		System.out.printf("13º salário proporcional: R$ %.2f%n", decimoTerceiro);
		if (avisoPrevio > 0)
			System.out.printf("Aviso Prévio indenizado: R$ %.2f%n", avisoPrevio);
		if (multaFgts > 0)
			System.out.printf("Multa de 40% do FGTS: R$ %.2f%n", multaFgts);
		System.out.printf("Total da Rescisão: R$ %.2f%n", total);
	}
}
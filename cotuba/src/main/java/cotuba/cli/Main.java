package cotuba.cli;

import java.nio.file.Path;

import cotuba.application.Cotuba;

public class Main {

	public static void main(String[] args) {
		Path diretorioDosMD;
		String formato;
		Path arquivoDeSaida;
		boolean modoVerboso = false;

		try {
			var opcoesCLI = new LeitorOpcoesCLI(args);

			diretorioDosMD = opcoesCLI.getDiretorioDosMD();
			formato = opcoesCLI.getFormato();
			arquivoDeSaida = opcoesCLI.getArquivoDeSaida();
			modoVerboso = opcoesCLI.isModoVerboso();
			
			var cotuba = new Cotuba();
			cotuba.executar(formato, diretorioDosMD, arquivoDeSaida);

			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}

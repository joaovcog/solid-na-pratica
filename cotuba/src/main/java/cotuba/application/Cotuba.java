package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.pdf.GeradorPDF;

public class Cotuba {
	
	public void executar(String formato, Path diretorioDosMD, Path arquivoDeSaida) {
		var renderizador = new RenderizadorMDParaHTML();
		List<Capitulo> capitulos = renderizador.renderizar(diretorioDosMD);
		
		Ebook ebook = new Ebook();
		ebook.setFormato(formato);
		ebook.setArquivoDeSaida(arquivoDeSaida);
		ebook.setCapitulos(capitulos);

		if ("pdf".equals(formato)) {
			var geradorPDF = new GeradorPDF();
			geradorPDF.gerar(ebook);

		} else if ("epub".equals(formato)) {
			var geradorEPUB = new GeradorEPUB();
			geradorEPUB.gerar(ebook);
			
		} else {
			throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
		}
	}
	
}

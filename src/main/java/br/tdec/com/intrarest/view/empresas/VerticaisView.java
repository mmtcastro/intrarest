package br.tdec.com.intrarest.view.empresas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.tdec.com.intrarest.model.empresas.Vertical;
import br.tdec.com.intrarest.service.VerticalService;
import br.tdec.com.intrarest.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "verticais", layout = MainLayout.class)
@PageTitle("Verticais | Intra RESTAPI")
public class VerticaisView extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	@Autowired
	private VerticalService verticalService;
	private Grid<Vertical> grid;

	public VerticaisView(VerticalService verticalService) {
		grid = new Grid<>(Vertical.class, false);

		grid.addColumn(Vertical::getCodigo).setHeader("Codigo");
		grid.addColumn(Vertical::getDescricao).setHeader("Descrição");
		grid.addColumn(Vertical::getStatus).setHeader("Status");
		grid.addColumn(Vertical::getAutor).setHeader("Autor");
		grid.addColumn(Vertical::getCriacao).setHeader("Criação");
		grid.addColumn(Vertical::getId).setHeader("Id");

		List<Vertical> verticais = verticalService.findAll();
		grid.setItems(verticais);
		add(grid);
		verticais.forEach(v -> System.out.print("-> " + v.getCodigo()));

		System.out.println("Size eh " + verticais.size());
		System.out.println(verticais);
	}

	public VerticalService getVerticalService() {
		return verticalService;
	}

	public void setVerticalService(VerticalService verticalService) {
		this.verticalService = verticalService;
	}

}

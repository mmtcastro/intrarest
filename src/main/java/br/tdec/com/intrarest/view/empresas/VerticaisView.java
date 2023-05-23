package br.tdec.com.intrarest.view.empresas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import br.tdec.com.intrarest.model.empresas.Vertical;
import br.tdec.com.intrarest.service.VerticalService;
import br.tdec.com.intrarest.views.MainLayout;

@Route(value = "verticais", layout = MainLayout.class)
public class VerticaisView extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	@Autowired
	private VerticalService verticalService;
	private Grid<Vertical> grid;
	
	public VerticaisView(VerticalService verticalService) {
		grid = new Grid<>(Vertical.class, false);
		grid.addColumn(Vertical::getId).setHeader("Id");
		grid.addColumn(Vertical::getCodigo).setHeader("Codigo");
		grid.addColumn(Vertical::getDescricao).setHeader("Descrição");
		grid.addColumn(Vertical::getAutor).setHeader("Autor");

		List<Vertical> verticais = verticalService.findAll();
		grid.setItems(verticais);
		add(grid);
		
		System.out.println(verticalService.findAll());
	}

	public VerticalService getVerticalService() {
		return verticalService;
	}

	public void setVerticalService(VerticalService verticalService) {
		this.verticalService = verticalService;
	}

}

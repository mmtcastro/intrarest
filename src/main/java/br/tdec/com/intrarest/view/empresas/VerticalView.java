package br.tdec.com.intrarest.view.empresas;

import org.springframework.ldap.core.support.BaseLdapPathContextSource;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import br.tdec.com.intrarest.views.MainLayout;

@PageTitle("Vertical")
@Route(value = "vertical", layout = MainLayout.class)
public class VerticalView extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	private BaseLdapPathContextSource contextSource;

	private TextField id2;
	private TextField codigo;
	private TextField descricao;
	private Button save;
	private Button cancel;

	public VerticalView(BaseLdapPathContextSource contextSource) {
		id2 = new TextField("Id");
		codigo = new TextField("Código");
		descricao = new TextField("Descrição");
		save = new Button("Save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickListener(e -> {
			save();
		});
		cancel = new Button("Cancel");

		setMargin(true);
		setVerticalComponentAlignment(Alignment.END, id2, codigo, descricao, save, cancel);

		add(id2, codigo, descricao, save, cancel);
	}

	private void save() {
		authenticate("marcelo", "Hodge$404");
	}

	public void authenticate(String username, String password) {
//		contextSource.getContext("cn=" + username + ",o=TDec," + env.getRequiredProperty("ldap.partitionSuffix"),
//				password);
	}

	public TextField getId2() {
		return id2;
	}

	public void setId2(TextField id2) {
		this.id2 = id2;
	}

	public TextField getCodigo() {
		return codigo;
	}

	public void setCodigo(TextField codigo) {
		this.codigo = codigo;
	}

	public TextField getDescricao() {
		return descricao;
	}

	public void setDescricao(TextField descricao) {
		this.descricao = descricao;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

}

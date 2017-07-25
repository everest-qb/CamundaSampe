package sunspring;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

@Named
@ViewScoped
public class ViewBean implements Serializable{
	private static final long serialVersionUID = 1371150964916189135L;

	private List<SwfWfApproveLine> list;

	@Inject
	private DataService service;

	@PostConstruct
	public void init() {
		list = service.fkindAll();
	}

	public void onRowEdit(RowEditEvent event) {
		service.save(list);
	}

	public void onRowCancel(RowEditEvent event) {
		/*FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);*/
	}

	public void save() {
		service.save(list);
	}

	public List<SwfWfApproveLine> getList() {
		return list;
	}

	public void setList(List<SwfWfApproveLine> list) {
		this.list = list;
	}
}

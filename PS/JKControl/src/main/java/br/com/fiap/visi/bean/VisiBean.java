package br.com.fiap.visi.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.visi.dao.VisiDao;
import br.com.fiap.visi.model.Visi;
import br.com.fiap.visi.service.UploadService;

@Named
@RequestScoped
public class VisiBean {

	private Visi setup = new Visi();
	
	@Inject
	private VisiDao dao;
	
	private UploadedFile image;

	public String save() {
		System.out.println(setup);
		
		String path = UploadService.write(image, "setups");
		setup.setImagePath(path);
		
		dao.create(setup);
		
		showMessage("Setup cadastrado com sucesso");
		
		return "setups?faces-redirect=true";
	}
	
	public List<Visi> list() {
		return dao.listAll();
	}
	
	public String remove(Visi setup) {
		dao.delete(setup);
		showMessage("Setup apagado com sucesso");
		return "setups?faces-redirect=true";
	}
	
	public void edit() {
		dao.update(setup);
		showMessage("Setup atualizado com sucesso");
	}

	private void showMessage(String msg) {
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
		
		FacesMessage facesMessage = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public Visi getSetup() {
		return setup;
	}

	public void setSetup(Visi setup) {
		this.setup = setup;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}
	
}

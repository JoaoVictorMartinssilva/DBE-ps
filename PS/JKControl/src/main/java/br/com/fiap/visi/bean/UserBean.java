package br.com.fiap.visi.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.visi.dao.UserDao;
import br.com.fiap.visi.model.User;
import br.com.fiap.visi.service.UploadService;

@Named
@RequestScoped
public class UserBean {
	
	private User user = new User();
	
	
	
	@Inject
	private UserDao dao;
	
	private UploadedFile image;
	
	public String save() {
		System.out.println(user);
		
		String path = UploadService.write(image, "user");
		user.setImagePath(path);
		
		dao.create(user);
		
		FacesMessage facesMessage = new FacesMessage("UsuÃ¡rio cadastrado com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
		return "users";
	}
	
	public List<User> list() {
		return dao.listAll();
	}
	
	public String login() {
		if(dao.exist(user)) {
			FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.put("user", user);
			
			return "setups";
		}
		
		FacesMessage facesMessage = new FacesMessage("Login invÃ¡lido");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
		return "login";
		
	}
	
	public String logout() {
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.remove("user");
		
		return "login";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}



}

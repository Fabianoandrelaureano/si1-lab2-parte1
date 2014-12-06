package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Compara;
import models.Meta;
import models.dao.GenericDAO;

import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	private static final GenericDAO dao = new GenericDAO();
	
	@Transactional
    public static Result index() {
    	List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
    	Collections.sort(metas, new Compara());
    	
    	
    	return ok(index.render(metas));
    }
    
    @Transactional
    public static Result addMeta(){
        DynamicForm form = Form.form().bindFromRequest();

        String nome = form.get("nome");
        String descricao = form.get("descricao");
        String data = form.get("data");
        String prioridade = form.get("prioridade");

        Meta meta = new Meta(nome, descricao, data, prioridade);
        dao.persist(meta);

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        return ok(index.render(metas));
    }
    
    @Transactional
    public static Result criaMeta(){
    	return ok(cadastro.render("Criar meta"));
    }
    
    @Transactional
    public static Result remove(){
    	List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
    	return ok(remover.render(metas));
    }
    
    @Transactional
	public static Result removeMeta() {
		DynamicForm requestData = Form.form().bindFromRequest();
		long id = Long.parseLong(requestData.get("opcoes"));

		dao.removeById(Meta.class, id);
		dao.flush();
		return redirect(routes.Application.index());
	}
    
    @Transactional
    public static Result concluir() {
    	DynamicForm requestData = Form.form().bindFromRequest();
		long id = Long.parseLong(requestData.get("opcoes"));
		
		List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
		
		for (int i = 0; i < metas.size(); i++) {
			if (metas.get(i).getId().equals(id)) {
				metas.get(i).setStatus();
			}
		}
		
		return redirect(routes.Application.index());
		
    }
    
    @Transactional
    public static Result info() {
    	List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
    	
    	List<Long> semanas = new ArrayList<Long>();
		
		long aux = 0;
		for (Meta meta : metas) {
			if(aux != meta.getSemana()){
				aux = meta.getSemana();
				semanas.add(aux);
			}
		}
		
		int concluidas = 0; 
		int nConcluidas = 0;
		
		for (int i = 0; i < metas.size(); i++) {
			if (metas.get(i).isConcluida() == "Concluida") {
				concluidas += 1;
			}else {
				nConcluidas += 1;
			}
				
		}
		
		Collections.sort(metas, new Compara());
		Collections.sort(semanas);
		
		return ok(info.render(metas, semanas, concluidas, nConcluidas));
		
    }

}

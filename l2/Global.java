import play.*;
import models.dao.GenericDAO;
//import models.dao.GenericDAOImpl;
import play.db.jpa.JPA;
import models.*;

import java.util.List;

public class Global extends GlobalSettings{
	
	private static GenericDAO dao = new GenericDAO();;

	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");
		
		JPA.withTransaction(new play.libs.F.Callback0() {
			
			@Override
			public void invoke() throws Throwable {
	    		
				Meta[] metas = {new Meta("Terminar a primeira parte do lab2", "muito importante", "11/30/2014", "Alta"), 
						new Meta("Terminar a segunda parte do lab2", "muito importante", "12/05/2014", "Alta"), 
						new Meta("Aprender mais o bootstrap", "para melhorar o desing das paginas", "11/30/2014", "Baixa"), 
						new Meta("Acabar os exercicios de espaço vetorial", "necessario para a prova", "12/30/2014", "baixa"),				
						new Meta("Estudar um pouco mais javascript", "precisa nos labs", "12/30/2014", "baixa"),
						new Meta("meta6", "mais uma meta", "12/05/2014", "baixa"),
						new Meta("meta7", "mais uma meta", "12/15/2014", "baixa"),
						new Meta("meta8", "mais uma meta", "12/25/2014", "baixa"),
						new Meta("meta9", "mais uma meta", "12/16/2014", "baixa"),
						new Meta("meta10", "mais uma meta", "12/05/2014", "baixa")};
				
				for (Meta meta:metas){
					dao.persist(meta);
					dao.flush();
				}
				
			}
					    			    		
		});
		
	}
	
	@Override
	public void onStop(Application app) {
		JPA.withTransaction(new play.libs.F.Callback0() {
		    @Override
		    public void invoke() throws Throwable {
		        Logger.info("Aplicação finalizando...");
		        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());

		        for (Meta meta : metas) {
		        dao.removeById(Meta.class, meta.getId());
		       } 
		    }
		}); 
	}
	
}




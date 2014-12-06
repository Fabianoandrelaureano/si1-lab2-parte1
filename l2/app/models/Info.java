package models;

import java.util.List;

import models.dao.GenericDAO;

public class Info {
	
	private static final GenericDAO dao = new GenericDAO();
		
	public int numeroDeMetasPorSemana(String data) {
		List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
		int numMetas = 0;
		
		for (int i = 0; i < metas.size(); i++) {
			if (metas.get(i).getData().equals(data)) {
				
			}
		}
		
		return numMetas;
		
	}

}

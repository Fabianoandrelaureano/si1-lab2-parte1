import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import models.Meta;
import models.dao.GenericDAO;

public class MinhasMetasTest {
	
	private GenericDAO dao = new GenericDAO();
	
	@Test
    public void deveIniciarSemMetas() throws Exception {
        List<Meta> disciplinas = dao.findAllByClassName(Meta.class.getName());
        assertThat(disciplinas).isEmpty();
    }
	
	@Test
    public void deveSalvarMetaNoBD() throws Exception {
        Meta meta = new Meta("Aprender algo", "rapido", "hoje", "alta");
        dao.persist(meta);

        List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
        assertThat(metas.size()).isEqualTo(1);
        assertThat(metas.get(0).getNome()).isEqualTo("Aprender algo");
    }
	
	/*@Test
    public void deveRemoverMetaDoBD() throws Exception {
        Meta meta = new Meta("Aprender algo", "rapido", "hoje", "alta");
        
        long id = 11111111;
        
        meta.setId(id);
        
        dao.persist(meta);

        dao.removeById(Meta.class, meta.getId());
		dao.flush();
		
		List<Meta> metas = dao.findAllByClassName(Meta.class.getName());
		
		assertEquals(metas.contains(meta), false);
        
    }*/
	
	@Test
	public void deveMostrarASemanaDaMeta() {
		 Meta meta = new Meta("Aprender algo", "rapido", "01/01/2014", "alta");
		 
		 assertEquals(meta.getSemana(), 1);
		 
	}
	
	public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }



}

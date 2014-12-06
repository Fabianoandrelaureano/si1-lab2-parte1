import static org.fest.assertions.Assertions.assertThat;
import models.Compara;
import models.Meta;

import org.junit.Test;


public class ComparaTest {
	
	@Test
	public void testeDoCompara() {
		Compara c = new Compara();
		
		Meta meta1 = new Meta("Aprender algo1", "rapido", "11/02/2014", "alta");
		Meta meta2 = new Meta("Aprender algo2", "rapido", "11/02/2014", "alta");
		Meta meta3 = new Meta("Aprender algo3", "rapido", "11/22/2014", "alta");
		
		assertThat(c.compare(meta1, meta2)).isEqualTo(0);
		assertThat(c.compare(meta1, meta3)).isEqualTo(-1);
		assertThat(c.compare(meta3, meta2)).isEqualTo(1);
		
	}

}

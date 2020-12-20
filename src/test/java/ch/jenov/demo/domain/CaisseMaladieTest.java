package ch.jenov.demo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ch.jenov.demo.web.rest.TestUtil;

public class CaisseMaladieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaisseMaladie.class);
        CaisseMaladie caisseMaladie1 = new CaisseMaladie();
        caisseMaladie1.setId(1L);
        CaisseMaladie caisseMaladie2 = new CaisseMaladie();
        caisseMaladie2.setId(caisseMaladie1.getId());
        assertThat(caisseMaladie1).isEqualTo(caisseMaladie2);
        caisseMaladie2.setId(2L);
        assertThat(caisseMaladie1).isNotEqualTo(caisseMaladie2);
        caisseMaladie1.setId(null);
        assertThat(caisseMaladie1).isNotEqualTo(caisseMaladie2);
    }
}

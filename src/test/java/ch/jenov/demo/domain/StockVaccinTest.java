package ch.jenov.demo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ch.jenov.demo.web.rest.TestUtil;

public class StockVaccinTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StockVaccin.class);
        StockVaccin stockVaccin1 = new StockVaccin();
        stockVaccin1.setId(1L);
        StockVaccin stockVaccin2 = new StockVaccin();
        stockVaccin2.setId(stockVaccin1.getId());
        assertThat(stockVaccin1).isEqualTo(stockVaccin2);
        stockVaccin2.setId(2L);
        assertThat(stockVaccin1).isNotEqualTo(stockVaccin2);
        stockVaccin1.setId(null);
        assertThat(stockVaccin1).isNotEqualTo(stockVaccin2);
    }
}

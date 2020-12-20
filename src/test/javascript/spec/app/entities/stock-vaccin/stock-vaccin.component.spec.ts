import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JenovDemoAppTestModule } from '../../../test.module';
import { StockVaccinComponent } from 'app/entities/stock-vaccin/stock-vaccin.component';
import { StockVaccinService } from 'app/entities/stock-vaccin/stock-vaccin.service';
import { StockVaccin } from 'app/shared/model/stock-vaccin.model';

describe('Component Tests', () => {
  describe('StockVaccin Management Component', () => {
    let comp: StockVaccinComponent;
    let fixture: ComponentFixture<StockVaccinComponent>;
    let service: StockVaccinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [StockVaccinComponent],
      })
        .overrideTemplate(StockVaccinComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StockVaccinComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StockVaccinService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new StockVaccin(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.stockVaccins && comp.stockVaccins[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JenovDemoAppTestModule } from '../../../test.module';
import { StockVaccinDetailComponent } from 'app/entities/stock-vaccin/stock-vaccin-detail.component';
import { StockVaccin } from 'app/shared/model/stock-vaccin.model';

describe('Component Tests', () => {
  describe('StockVaccin Management Detail Component', () => {
    let comp: StockVaccinDetailComponent;
    let fixture: ComponentFixture<StockVaccinDetailComponent>;
    const route = ({ data: of({ stockVaccin: new StockVaccin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [StockVaccinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StockVaccinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StockVaccinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load stockVaccin on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.stockVaccin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

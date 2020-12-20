import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JenovDemoAppTestModule } from '../../../test.module';
import { StockVaccinUpdateComponent } from 'app/entities/stock-vaccin/stock-vaccin-update.component';
import { StockVaccinService } from 'app/entities/stock-vaccin/stock-vaccin.service';
import { StockVaccin } from 'app/shared/model/stock-vaccin.model';

describe('Component Tests', () => {
  describe('StockVaccin Management Update Component', () => {
    let comp: StockVaccinUpdateComponent;
    let fixture: ComponentFixture<StockVaccinUpdateComponent>;
    let service: StockVaccinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [StockVaccinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StockVaccinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StockVaccinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StockVaccinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StockVaccin(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new StockVaccin();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

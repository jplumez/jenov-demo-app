import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JenovDemoAppTestModule } from '../../../test.module';
import { CaisseMaladieUpdateComponent } from 'app/entities/caisse-maladie/caisse-maladie-update.component';
import { CaisseMaladieService } from 'app/entities/caisse-maladie/caisse-maladie.service';
import { CaisseMaladie } from 'app/shared/model/caisse-maladie.model';

describe('Component Tests', () => {
  describe('CaisseMaladie Management Update Component', () => {
    let comp: CaisseMaladieUpdateComponent;
    let fixture: ComponentFixture<CaisseMaladieUpdateComponent>;
    let service: CaisseMaladieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [CaisseMaladieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CaisseMaladieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaisseMaladieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaisseMaladieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaisseMaladie(123);
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
        const entity = new CaisseMaladie();
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

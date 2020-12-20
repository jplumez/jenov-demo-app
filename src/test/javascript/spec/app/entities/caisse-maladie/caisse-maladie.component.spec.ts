import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JenovDemoAppTestModule } from '../../../test.module';
import { CaisseMaladieComponent } from 'app/entities/caisse-maladie/caisse-maladie.component';
import { CaisseMaladieService } from 'app/entities/caisse-maladie/caisse-maladie.service';
import { CaisseMaladie } from 'app/shared/model/caisse-maladie.model';

describe('Component Tests', () => {
  describe('CaisseMaladie Management Component', () => {
    let comp: CaisseMaladieComponent;
    let fixture: ComponentFixture<CaisseMaladieComponent>;
    let service: CaisseMaladieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [CaisseMaladieComponent],
      })
        .overrideTemplate(CaisseMaladieComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaisseMaladieComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaisseMaladieService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CaisseMaladie(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.caisseMaladies && comp.caisseMaladies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

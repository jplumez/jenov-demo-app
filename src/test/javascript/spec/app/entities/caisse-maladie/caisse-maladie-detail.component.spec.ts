import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JenovDemoAppTestModule } from '../../../test.module';
import { CaisseMaladieDetailComponent } from 'app/entities/caisse-maladie/caisse-maladie-detail.component';
import { CaisseMaladie } from 'app/shared/model/caisse-maladie.model';

describe('Component Tests', () => {
  describe('CaisseMaladie Management Detail Component', () => {
    let comp: CaisseMaladieDetailComponent;
    let fixture: ComponentFixture<CaisseMaladieDetailComponent>;
    const route = ({ data: of({ caisseMaladie: new CaisseMaladie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [CaisseMaladieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CaisseMaladieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaisseMaladieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load caisseMaladie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.caisseMaladie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

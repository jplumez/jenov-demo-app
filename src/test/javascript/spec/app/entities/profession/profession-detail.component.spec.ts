import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JenovDemoAppTestModule } from '../../../test.module';
import { ProfessionDetailComponent } from 'app/entities/profession/profession-detail.component';
import { Profession } from 'app/shared/model/profession.model';

describe('Component Tests', () => {
  describe('Profession Management Detail Component', () => {
    let comp: ProfessionDetailComponent;
    let fixture: ComponentFixture<ProfessionDetailComponent>;
    const route = ({ data: of({ profession: new Profession(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [ProfessionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProfessionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProfessionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load profession on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.profession).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

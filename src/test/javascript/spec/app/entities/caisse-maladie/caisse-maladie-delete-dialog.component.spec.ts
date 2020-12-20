import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JenovDemoAppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { CaisseMaladieDeleteDialogComponent } from 'app/entities/caisse-maladie/caisse-maladie-delete-dialog.component';
import { CaisseMaladieService } from 'app/entities/caisse-maladie/caisse-maladie.service';

describe('Component Tests', () => {
  describe('CaisseMaladie Management Delete Component', () => {
    let comp: CaisseMaladieDeleteDialogComponent;
    let fixture: ComponentFixture<CaisseMaladieDeleteDialogComponent>;
    let service: CaisseMaladieService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JenovDemoAppTestModule],
        declarations: [CaisseMaladieDeleteDialogComponent],
      })
        .overrideTemplate(CaisseMaladieDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaisseMaladieDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaisseMaladieService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';

@Component({
  templateUrl: './profession-delete-dialog.component.html',
})
export class ProfessionDeleteDialogComponent {
  profession?: IProfession;

  constructor(
    protected professionService: ProfessionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.professionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('professionListModification');
      this.activeModal.close();
    });
  }
}

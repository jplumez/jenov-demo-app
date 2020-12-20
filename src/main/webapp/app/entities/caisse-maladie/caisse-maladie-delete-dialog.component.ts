import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { CaisseMaladieService } from './caisse-maladie.service';

@Component({
  templateUrl: './caisse-maladie-delete-dialog.component.html',
})
export class CaisseMaladieDeleteDialogComponent {
  caisseMaladie?: ICaisseMaladie;

  constructor(
    protected caisseMaladieService: CaisseMaladieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.caisseMaladieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('caisseMaladieListModification');
      this.activeModal.close();
    });
  }
}

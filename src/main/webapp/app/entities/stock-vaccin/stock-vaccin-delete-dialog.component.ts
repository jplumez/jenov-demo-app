import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';
import { StockVaccinService } from './stock-vaccin.service';

@Component({
  templateUrl: './stock-vaccin-delete-dialog.component.html',
})
export class StockVaccinDeleteDialogComponent {
  stockVaccin?: IStockVaccin;

  constructor(
    protected stockVaccinService: StockVaccinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stockVaccinService.delete(id).subscribe(() => {
      this.eventManager.broadcast('stockVaccinListModification');
      this.activeModal.close();
    });
  }
}

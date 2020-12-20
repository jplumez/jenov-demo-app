import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';
import { StockVaccinService } from './stock-vaccin.service';
import { StockVaccinDeleteDialogComponent } from './stock-vaccin-delete-dialog.component';

@Component({
  selector: 'jhi-stock-vaccin',
  templateUrl: './stock-vaccin.component.html',
})
export class StockVaccinComponent implements OnInit, OnDestroy {
  stockVaccins?: IStockVaccin[];
  eventSubscriber?: Subscription;

  constructor(
    protected stockVaccinService: StockVaccinService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.stockVaccinService.query().subscribe((res: HttpResponse<IStockVaccin[]>) => (this.stockVaccins = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStockVaccins();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStockVaccin): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStockVaccins(): void {
    this.eventSubscriber = this.eventManager.subscribe('stockVaccinListModification', () => this.loadAll());
  }

  delete(stockVaccin: IStockVaccin): void {
    const modalRef = this.modalService.open(StockVaccinDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.stockVaccin = stockVaccin;
  }
}

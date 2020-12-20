import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';

@Component({
  selector: 'jhi-stock-vaccin-detail',
  templateUrl: './stock-vaccin-detail.component.html',
})
export class StockVaccinDetailComponent implements OnInit {
  stockVaccin: IStockVaccin | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stockVaccin }) => (this.stockVaccin = stockVaccin));
  }

  previousState(): void {
    window.history.back();
  }
}

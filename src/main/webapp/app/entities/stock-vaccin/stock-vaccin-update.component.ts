import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStockVaccin, StockVaccin } from 'app/shared/model/stock-vaccin.model';
import { StockVaccinService } from './stock-vaccin.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre/centre.service';

@Component({
  selector: 'jhi-stock-vaccin-update',
  templateUrl: './stock-vaccin-update.component.html',
})
export class StockVaccinUpdateComponent implements OnInit {
  isSaving = false;
  centres: ICentre[] = [];

  editForm = this.fb.group({
    id: [],
    stockInitial: [],
    stockActuel: [],
    centre: [],
  });

  constructor(
    protected stockVaccinService: StockVaccinService,
    protected centreService: CentreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stockVaccin }) => {
      this.updateForm(stockVaccin);

      this.centreService.query().subscribe((res: HttpResponse<ICentre[]>) => (this.centres = res.body || []));
    });
  }

  updateForm(stockVaccin: IStockVaccin): void {
    this.editForm.patchValue({
      id: stockVaccin.id,
      stockInitial: stockVaccin.stockInitial,
      stockActuel: stockVaccin.stockActuel,
      centre: stockVaccin.centre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stockVaccin = this.createFromForm();
    if (stockVaccin.id !== undefined) {
      this.subscribeToSaveResponse(this.stockVaccinService.update(stockVaccin));
    } else {
      this.subscribeToSaveResponse(this.stockVaccinService.create(stockVaccin));
    }
  }

  private createFromForm(): IStockVaccin {
    return {
      ...new StockVaccin(),
      id: this.editForm.get(['id'])!.value,
      stockInitial: this.editForm.get(['stockInitial'])!.value,
      stockActuel: this.editForm.get(['stockActuel'])!.value,
      centre: this.editForm.get(['centre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStockVaccin>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICentre): any {
    return item.id;
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVaccination, Vaccination } from 'app/shared/model/vaccination.model';
import { VaccinationService } from './vaccination.service';
import { IPatient } from 'app/shared/model/patient.model';
import { PatientService } from 'app/entities/patient/patient.service';
import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';
import { StockVaccinService } from 'app/entities/stock-vaccin/stock-vaccin.service';

type SelectableEntity = IPatient | IStockVaccin;

@Component({
  selector: 'jhi-vaccination-update',
  templateUrl: './vaccination-update.component.html',
})
export class VaccinationUpdateComponent implements OnInit {
  isSaving = false;
  patients: IPatient[] = [];
  stockvaccins: IStockVaccin[] = [];

  editForm = this.fb.group({
    id: [],
    dateVaccin: [],
    personne: [],
    stockVaccin: [],
  });

  constructor(
    protected vaccinationService: VaccinationService,
    protected patientService: PatientService,
    protected stockVaccinService: StockVaccinService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vaccination }) => {
      if (!vaccination.id) {
        const today = moment().startOf('day');
        vaccination.dateVaccin = today;
      }

      this.updateForm(vaccination);

      this.patientService.query().subscribe((res: HttpResponse<IPatient[]>) => (this.patients = res.body || []));

      this.stockVaccinService.query().subscribe((res: HttpResponse<IStockVaccin[]>) => (this.stockvaccins = res.body || []));
    });
  }

  updateForm(vaccination: IVaccination): void {
    this.editForm.patchValue({
      id: vaccination.id,
      dateVaccin: vaccination.dateVaccin ? vaccination.dateVaccin.format(DATE_TIME_FORMAT) : null,
      personne: vaccination.personne,
      stockVaccin: vaccination.stockVaccin,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vaccination = this.createFromForm();
    if (vaccination.id !== undefined) {
      this.subscribeToSaveResponse(this.vaccinationService.update(vaccination));
    } else {
      this.subscribeToSaveResponse(this.vaccinationService.create(vaccination));
    }
  }

  private createFromForm(): IVaccination {
    return {
      ...new Vaccination(),
      id: this.editForm.get(['id'])!.value,
      dateVaccin: this.editForm.get(['dateVaccin'])!.value ? moment(this.editForm.get(['dateVaccin'])!.value, DATE_TIME_FORMAT) : undefined,
      personne: this.editForm.get(['personne'])!.value,
      stockVaccin: this.editForm.get(['stockVaccin'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVaccination>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}

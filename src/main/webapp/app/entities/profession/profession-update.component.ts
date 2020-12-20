import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProfession, Profession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';

@Component({
  selector: 'jhi-profession-update',
  templateUrl: './profession-update.component.html',
})
export class ProfessionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
  });

  constructor(protected professionService: ProfessionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profession }) => {
      this.updateForm(profession);
    });
  }

  updateForm(profession: IProfession): void {
    this.editForm.patchValue({
      id: profession.id,
      nom: profession.nom,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const profession = this.createFromForm();
    if (profession.id !== undefined) {
      this.subscribeToSaveResponse(this.professionService.update(profession));
    } else {
      this.subscribeToSaveResponse(this.professionService.create(profession));
    }
  }

  private createFromForm(): IProfession {
    return {
      ...new Profession(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfession>>): void {
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
}

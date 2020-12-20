import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICaisseMaladie, CaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { CaisseMaladieService } from './caisse-maladie.service';

@Component({
  selector: 'jhi-caisse-maladie-update',
  templateUrl: './caisse-maladie-update.component.html',
})
export class CaisseMaladieUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numeroCaisseMaladie: [],
    nom: [],
    adresse: [],
    npaLocalite: [],
    telephone: [],
    fax: [],
    email: [],
  });

  constructor(protected caisseMaladieService: CaisseMaladieService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caisseMaladie }) => {
      this.updateForm(caisseMaladie);
    });
  }

  updateForm(caisseMaladie: ICaisseMaladie): void {
    this.editForm.patchValue({
      id: caisseMaladie.id,
      numeroCaisseMaladie: caisseMaladie.numeroCaisseMaladie,
      nom: caisseMaladie.nom,
      adresse: caisseMaladie.adresse,
      npaLocalite: caisseMaladie.npaLocalite,
      telephone: caisseMaladie.telephone,
      fax: caisseMaladie.fax,
      email: caisseMaladie.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caisseMaladie = this.createFromForm();
    if (caisseMaladie.id !== undefined) {
      this.subscribeToSaveResponse(this.caisseMaladieService.update(caisseMaladie));
    } else {
      this.subscribeToSaveResponse(this.caisseMaladieService.create(caisseMaladie));
    }
  }

  private createFromForm(): ICaisseMaladie {
    return {
      ...new CaisseMaladie(),
      id: this.editForm.get(['id'])!.value,
      numeroCaisseMaladie: this.editForm.get(['numeroCaisseMaladie'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      npaLocalite: this.editForm.get(['npaLocalite'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaisseMaladie>>): void {
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

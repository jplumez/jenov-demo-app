import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';
import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { CaisseMaladieService } from 'app/entities/caisse-maladie/caisse-maladie.service';
import { IProfessionnelSante } from 'app/shared/model/professionnel-sante.model';
import { ProfessionnelSanteService } from 'app/entities/professionnel-sante/professionnel-sante.service';

type SelectableEntity = ICaisseMaladie | IProfessionnelSante;

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html',
})
export class PatientUpdateComponent implements OnInit {
  isSaving = false;
  caissemaladies: ICaisseMaladie[] = [];
  professionnelsantes: IProfessionnelSante[] = [];
  dateNaissanceDp: any;

  editForm = this.fb.group({
    id: [],
    noAvs: [null, [Validators.required]],
    dateNaissance: [],
    nom: [],
    prenom: [],
    adresse: [],
    npaLocalite: [],
    telephone: [],
    email: [],
    numeroAssure: [],
    caisseMaladie: [],
    medecinTraitant: [],
  });

  constructor(
    protected patientService: PatientService,
    protected caisseMaladieService: CaisseMaladieService,
    protected professionnelSanteService: ProfessionnelSanteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.updateForm(patient);

      this.caisseMaladieService.query().subscribe((res: HttpResponse<ICaisseMaladie[]>) => (this.caissemaladies = res.body || []));

      this.professionnelSanteService
        .query()
        .subscribe((res: HttpResponse<IProfessionnelSante[]>) => (this.professionnelsantes = res.body || []));
    });
  }

  updateForm(patient: IPatient): void {
    this.editForm.patchValue({
      id: patient.id,
      noAvs: patient.noAvs,
      dateNaissance: patient.dateNaissance,
      nom: patient.nom,
      prenom: patient.prenom,
      adresse: patient.adresse,
      npaLocalite: patient.npaLocalite,
      telephone: patient.telephone,
      email: patient.email,
      numeroAssure: patient.numeroAssure,
      caisseMaladie: patient.caisseMaladie,
      medecinTraitant: patient.medecinTraitant,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    return {
      ...new Patient(),
      id: this.editForm.get(['id'])!.value,
      noAvs: this.editForm.get(['noAvs'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      npaLocalite: this.editForm.get(['npaLocalite'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      email: this.editForm.get(['email'])!.value,
      numeroAssure: this.editForm.get(['numeroAssure'])!.value,
      caisseMaladie: this.editForm.get(['caisseMaladie'])!.value,
      medecinTraitant: this.editForm.get(['medecinTraitant'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
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

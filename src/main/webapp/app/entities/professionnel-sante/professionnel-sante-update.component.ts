import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProfessionnelSante, ProfessionnelSante } from 'app/shared/model/professionnel-sante.model';
import { ProfessionnelSanteService } from './professionnel-sante.service';
import { ILocalite } from 'app/shared/model/localite.model';
import { LocaliteService } from 'app/entities/localite/localite.service';
import { IProfession } from 'app/shared/model/profession.model';
import { ProfessionService } from 'app/entities/profession/profession.service';

type SelectableEntity = ILocalite | IProfession;

@Component({
  selector: 'jhi-professionnel-sante-update',
  templateUrl: './professionnel-sante-update.component.html',
})
export class ProfessionnelSanteUpdateComponent implements OnInit {
  isSaving = false;
  localites: ILocalite[] = [];
  professions: IProfession[] = [];

  editForm = this.fb.group({
    id: [],
    ide: [],
    prenom: [],
    nom: [],
    sexe: [],
    rue: [],
    localite: [],
    profession: [],
  });

  constructor(
    protected professionnelSanteService: ProfessionnelSanteService,
    protected localiteService: LocaliteService,
    protected professionService: ProfessionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ professionnelSante }) => {
      this.updateForm(professionnelSante);

      this.localiteService.query().subscribe((res: HttpResponse<ILocalite[]>) => (this.localites = res.body || []));

      this.professionService.query().subscribe((res: HttpResponse<IProfession[]>) => (this.professions = res.body || []));
    });
  }

  updateForm(professionnelSante: IProfessionnelSante): void {
    this.editForm.patchValue({
      id: professionnelSante.id,
      ide: professionnelSante.ide,
      prenom: professionnelSante.prenom,
      nom: professionnelSante.nom,
      sexe: professionnelSante.sexe,
      rue: professionnelSante.rue,
      localite: professionnelSante.localite,
      profession: professionnelSante.profession,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const professionnelSante = this.createFromForm();
    if (professionnelSante.id !== undefined) {
      this.subscribeToSaveResponse(this.professionnelSanteService.update(professionnelSante));
    } else {
      this.subscribeToSaveResponse(this.professionnelSanteService.create(professionnelSante));
    }
  }

  private createFromForm(): IProfessionnelSante {
    return {
      ...new ProfessionnelSante(),
      id: this.editForm.get(['id'])!.value,
      ide: this.editForm.get(['ide'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      rue: this.editForm.get(['rue'])!.value,
      localite: this.editForm.get(['localite'])!.value,
      profession: this.editForm.get(['profession'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfessionnelSante>>): void {
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

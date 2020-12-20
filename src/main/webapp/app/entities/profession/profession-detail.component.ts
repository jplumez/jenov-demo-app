import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfession } from 'app/shared/model/profession.model';

@Component({
  selector: 'jhi-profession-detail',
  templateUrl: './profession-detail.component.html',
})
export class ProfessionDetailComponent implements OnInit {
  profession: IProfession | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profession }) => (this.profession = profession));
  }

  previousState(): void {
    window.history.back();
  }
}

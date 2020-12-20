import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';

@Component({
  selector: 'jhi-caisse-maladie-detail',
  templateUrl: './caisse-maladie-detail.component.html',
})
export class CaisseMaladieDetailComponent implements OnInit {
  caisseMaladie: ICaisseMaladie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caisseMaladie }) => (this.caisseMaladie = caisseMaladie));
  }

  previousState(): void {
    window.history.back();
  }
}

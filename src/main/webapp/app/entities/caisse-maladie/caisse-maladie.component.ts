import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { CaisseMaladieService } from './caisse-maladie.service';
import { CaisseMaladieDeleteDialogComponent } from './caisse-maladie-delete-dialog.component';

@Component({
  selector: 'jhi-caisse-maladie',
  templateUrl: './caisse-maladie.component.html',
})
export class CaisseMaladieComponent implements OnInit, OnDestroy {
  caisseMaladies?: ICaisseMaladie[];
  eventSubscriber?: Subscription;

  constructor(
    protected caisseMaladieService: CaisseMaladieService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.caisseMaladieService.query().subscribe((res: HttpResponse<ICaisseMaladie[]>) => (this.caisseMaladies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCaisseMaladies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICaisseMaladie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCaisseMaladies(): void {
    this.eventSubscriber = this.eventManager.subscribe('caisseMaladieListModification', () => this.loadAll());
  }

  delete(caisseMaladie: ICaisseMaladie): void {
    const modalRef = this.modalService.open(CaisseMaladieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.caisseMaladie = caisseMaladie;
  }
}

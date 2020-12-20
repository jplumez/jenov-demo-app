import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from './personne.service';
import { PersonneDeleteDialogComponent } from './personne-delete-dialog.component';

@Component({
  selector: 'jhi-personne',
  templateUrl: './personne.component.html',
})
export class PersonneComponent implements OnInit, OnDestroy {
  personnes?: IPersonne[];
  eventSubscriber?: Subscription;

  constructor(protected personneService: PersonneService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.personneService.query().subscribe((res: HttpResponse<IPersonne[]>) => (this.personnes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonnes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonne): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonnes(): void {
    this.eventSubscriber = this.eventManager.subscribe('personneListModification', () => this.loadAll());
  }

  delete(personne: IPersonne): void {
    const modalRef = this.modalService.open(PersonneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personne = personne;
  }
}

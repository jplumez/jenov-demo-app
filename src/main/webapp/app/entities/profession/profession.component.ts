import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProfession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';
import { ProfessionDeleteDialogComponent } from './profession-delete-dialog.component';

@Component({
  selector: 'jhi-profession',
  templateUrl: './profession.component.html',
})
export class ProfessionComponent implements OnInit, OnDestroy {
  professions?: IProfession[];
  eventSubscriber?: Subscription;

  constructor(protected professionService: ProfessionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.professionService.query().subscribe((res: HttpResponse<IProfession[]>) => (this.professions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProfessions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProfession): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProfessions(): void {
    this.eventSubscriber = this.eventManager.subscribe('professionListModification', () => this.loadAll());
  }

  delete(profession: IProfession): void {
    const modalRef = this.modalService.open(ProfessionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.profession = profession;
  }
}

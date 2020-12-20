import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStockVaccin, StockVaccin } from 'app/shared/model/stock-vaccin.model';
import { StockVaccinService } from './stock-vaccin.service';
import { StockVaccinComponent } from './stock-vaccin.component';
import { StockVaccinDetailComponent } from './stock-vaccin-detail.component';
import { StockVaccinUpdateComponent } from './stock-vaccin-update.component';

@Injectable({ providedIn: 'root' })
export class StockVaccinResolve implements Resolve<IStockVaccin> {
  constructor(private service: StockVaccinService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStockVaccin> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((stockVaccin: HttpResponse<StockVaccin>) => {
          if (stockVaccin.body) {
            return of(stockVaccin.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StockVaccin());
  }
}

export const stockVaccinRoute: Routes = [
  {
    path: '',
    component: StockVaccinComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.stockVaccin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StockVaccinDetailComponent,
    resolve: {
      stockVaccin: StockVaccinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.stockVaccin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StockVaccinUpdateComponent,
    resolve: {
      stockVaccin: StockVaccinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.stockVaccin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StockVaccinUpdateComponent,
    resolve: {
      stockVaccin: StockVaccinResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.stockVaccin.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICaisseMaladie, CaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { CaisseMaladieService } from './caisse-maladie.service';
import { CaisseMaladieComponent } from './caisse-maladie.component';
import { CaisseMaladieDetailComponent } from './caisse-maladie-detail.component';
import { CaisseMaladieUpdateComponent } from './caisse-maladie-update.component';

@Injectable({ providedIn: 'root' })
export class CaisseMaladieResolve implements Resolve<ICaisseMaladie> {
  constructor(private service: CaisseMaladieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaisseMaladie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((caisseMaladie: HttpResponse<CaisseMaladie>) => {
          if (caisseMaladie.body) {
            return of(caisseMaladie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CaisseMaladie());
  }
}

export const caisseMaladieRoute: Routes = [
  {
    path: '',
    component: CaisseMaladieComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.caisseMaladie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CaisseMaladieDetailComponent,
    resolve: {
      caisseMaladie: CaisseMaladieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.caisseMaladie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CaisseMaladieUpdateComponent,
    resolve: {
      caisseMaladie: CaisseMaladieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.caisseMaladie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CaisseMaladieUpdateComponent,
    resolve: {
      caisseMaladie: CaisseMaladieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.caisseMaladie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

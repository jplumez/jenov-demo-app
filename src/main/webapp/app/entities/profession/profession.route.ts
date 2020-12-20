import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProfession, Profession } from 'app/shared/model/profession.model';
import { ProfessionService } from './profession.service';
import { ProfessionComponent } from './profession.component';
import { ProfessionDetailComponent } from './profession-detail.component';
import { ProfessionUpdateComponent } from './profession-update.component';

@Injectable({ providedIn: 'root' })
export class ProfessionResolve implements Resolve<IProfession> {
  constructor(private service: ProfessionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProfession> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((profession: HttpResponse<Profession>) => {
          if (profession.body) {
            return of(profession.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Profession());
  }
}

export const professionRoute: Routes = [
  {
    path: '',
    component: ProfessionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.profession.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProfessionDetailComponent,
    resolve: {
      profession: ProfessionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.profession.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProfessionUpdateComponent,
    resolve: {
      profession: ProfessionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.profession.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProfessionUpdateComponent,
    resolve: {
      profession: ProfessionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jenovDemoApp.profession.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

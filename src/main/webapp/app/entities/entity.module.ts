import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'personne',
        loadChildren: () => import('./personne/personne.module').then(m => m.JenovDemoAppPersonneModule),
      },
      {
        path: 'centre',
        loadChildren: () => import('./centre/centre.module').then(m => m.JenovDemoAppCentreModule),
      },
      {
        path: 'vaccination',
        loadChildren: () => import('./vaccination/vaccination.module').then(m => m.JenovDemoAppVaccinationModule),
      },
      {
        path: 'stock-vaccin',
        loadChildren: () => import('./stock-vaccin/stock-vaccin.module').then(m => m.JenovDemoAppStockVaccinModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JenovDemoAppEntityModule {}

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
      {
        path: 'localite',
        loadChildren: () => import('./localite/localite.module').then(m => m.JenovDemoAppLocaliteModule),
      },
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.JenovDemoAppPatientModule),
      },
      {
        path: 'caisse-maladie',
        loadChildren: () => import('./caisse-maladie/caisse-maladie.module').then(m => m.JenovDemoAppCaisseMaladieModule),
      },
      {
        path: 'profession',
        loadChildren: () => import('./profession/profession.module').then(m => m.JenovDemoAppProfessionModule),
      },
      {
        path: 'professionnel-sante',
        loadChildren: () => import('./professionnel-sante/professionnel-sante.module').then(m => m.JenovDemoAppProfessionnelSanteModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JenovDemoAppEntityModule {}

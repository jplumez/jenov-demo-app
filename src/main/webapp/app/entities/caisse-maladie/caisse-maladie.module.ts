import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JenovDemoAppSharedModule } from 'app/shared/shared.module';
import { CaisseMaladieComponent } from './caisse-maladie.component';
import { CaisseMaladieDetailComponent } from './caisse-maladie-detail.component';
import { CaisseMaladieUpdateComponent } from './caisse-maladie-update.component';
import { CaisseMaladieDeleteDialogComponent } from './caisse-maladie-delete-dialog.component';
import { caisseMaladieRoute } from './caisse-maladie.route';

@NgModule({
  imports: [JenovDemoAppSharedModule, RouterModule.forChild(caisseMaladieRoute)],
  declarations: [CaisseMaladieComponent, CaisseMaladieDetailComponent, CaisseMaladieUpdateComponent, CaisseMaladieDeleteDialogComponent],
  entryComponents: [CaisseMaladieDeleteDialogComponent],
})
export class JenovDemoAppCaisseMaladieModule {}

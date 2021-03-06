import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JenovDemoAppSharedModule } from 'app/shared/shared.module';
import { ProfessionnelSanteComponent } from './professionnel-sante.component';
import { ProfessionnelSanteDetailComponent } from './professionnel-sante-detail.component';
import { ProfessionnelSanteUpdateComponent } from './professionnel-sante-update.component';
import { ProfessionnelSanteDeleteDialogComponent } from './professionnel-sante-delete-dialog.component';
import { professionnelSanteRoute } from './professionnel-sante.route';

@NgModule({
  imports: [JenovDemoAppSharedModule, RouterModule.forChild(professionnelSanteRoute)],
  declarations: [
    ProfessionnelSanteComponent,
    ProfessionnelSanteDetailComponent,
    ProfessionnelSanteUpdateComponent,
    ProfessionnelSanteDeleteDialogComponent,
  ],
  entryComponents: [ProfessionnelSanteDeleteDialogComponent],
})
export class JenovDemoAppProfessionnelSanteModule {}

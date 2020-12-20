import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JenovDemoAppSharedModule } from 'app/shared/shared.module';
import { VaccinationComponent } from './vaccination.component';
import { VaccinationDetailComponent } from './vaccination-detail.component';
import { VaccinationUpdateComponent } from './vaccination-update.component';
import { VaccinationDeleteDialogComponent } from './vaccination-delete-dialog.component';
import { vaccinationRoute } from './vaccination.route';

@NgModule({
  imports: [JenovDemoAppSharedModule, RouterModule.forChild(vaccinationRoute)],
  declarations: [VaccinationComponent, VaccinationDetailComponent, VaccinationUpdateComponent, VaccinationDeleteDialogComponent],
  entryComponents: [VaccinationDeleteDialogComponent],
})
export class JenovDemoAppVaccinationModule {}

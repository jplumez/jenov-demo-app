import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JenovDemoAppSharedModule } from 'app/shared/shared.module';
import { ProfessionComponent } from './profession.component';
import { ProfessionDetailComponent } from './profession-detail.component';
import { ProfessionUpdateComponent } from './profession-update.component';
import { ProfessionDeleteDialogComponent } from './profession-delete-dialog.component';
import { professionRoute } from './profession.route';

@NgModule({
  imports: [JenovDemoAppSharedModule, RouterModule.forChild(professionRoute)],
  declarations: [ProfessionComponent, ProfessionDetailComponent, ProfessionUpdateComponent, ProfessionDeleteDialogComponent],
  entryComponents: [ProfessionDeleteDialogComponent],
})
export class JenovDemoAppProfessionModule {}

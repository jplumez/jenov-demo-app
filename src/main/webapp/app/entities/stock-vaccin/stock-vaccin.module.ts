import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JenovDemoAppSharedModule } from 'app/shared/shared.module';
import { StockVaccinComponent } from './stock-vaccin.component';
import { StockVaccinDetailComponent } from './stock-vaccin-detail.component';
import { StockVaccinUpdateComponent } from './stock-vaccin-update.component';
import { StockVaccinDeleteDialogComponent } from './stock-vaccin-delete-dialog.component';
import { stockVaccinRoute } from './stock-vaccin.route';

@NgModule({
  imports: [JenovDemoAppSharedModule, RouterModule.forChild(stockVaccinRoute)],
  declarations: [StockVaccinComponent, StockVaccinDetailComponent, StockVaccinUpdateComponent, StockVaccinDeleteDialogComponent],
  entryComponents: [StockVaccinDeleteDialogComponent],
})
export class JenovDemoAppStockVaccinModule {}

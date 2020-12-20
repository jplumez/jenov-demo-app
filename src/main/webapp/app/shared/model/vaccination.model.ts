import { Moment } from 'moment';
import { IPatient } from 'app/shared/model/patient.model';
import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';

export interface IVaccination {
  id?: number;
  dateVaccin?: Moment;
  personne?: IPatient;
  stockVaccin?: IStockVaccin;
}

export class Vaccination implements IVaccination {
  constructor(public id?: number, public dateVaccin?: Moment, public personne?: IPatient, public stockVaccin?: IStockVaccin) {}
}

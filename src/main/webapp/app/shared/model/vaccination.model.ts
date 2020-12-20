import { Moment } from 'moment';
import { IPersonne } from 'app/shared/model/personne.model';
import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';

export interface IVaccination {
  id?: number;
  dateVaccin?: Moment;
  personne?: IPersonne;
  stockVaccin?: IStockVaccin;
}

export class Vaccination implements IVaccination {
  constructor(public id?: number, public dateVaccin?: Moment, public personne?: IPersonne, public stockVaccin?: IStockVaccin) {}
}

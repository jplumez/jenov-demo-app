import { IVaccination } from 'app/shared/model/vaccination.model';
import { ICentre } from 'app/shared/model/centre.model';

export interface IStockVaccin {
  id?: number;
  stockInitial?: number;
  stockActuel?: number;
  vaccinations?: IVaccination[];
  centre?: ICentre;
}

export class StockVaccin implements IStockVaccin {
  constructor(
    public id?: number,
    public stockInitial?: number,
    public stockActuel?: number,
    public vaccinations?: IVaccination[],
    public centre?: ICentre
  ) {}
}

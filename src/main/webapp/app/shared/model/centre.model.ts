import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';

export interface ICentre {
  id?: number;
  code?: string;
  localite?: string;
  description?: string;
  stockVaccins?: IStockVaccin[];
}

export class Centre implements ICentre {
  constructor(
    public id?: number,
    public code?: string,
    public localite?: string,
    public description?: string,
    public stockVaccins?: IStockVaccin[]
  ) {}
}

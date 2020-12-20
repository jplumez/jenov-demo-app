import { IVaccination } from 'app/shared/model/vaccination.model';

export interface IPersonne {
  id?: number;
  name?: string;
  noAvs?: string;
  vaccinations?: IVaccination[];
}

export class Personne implements IPersonne {
  constructor(public id?: number, public name?: string, public noAvs?: string, public vaccinations?: IVaccination[]) {}
}

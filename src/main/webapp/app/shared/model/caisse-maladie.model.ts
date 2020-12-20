import { IPatient } from 'app/shared/model/patient.model';

export interface ICaisseMaladie {
  id?: number;
  numeroCaisseMaladie?: string;
  nom?: string;
  adresse?: string;
  npaLocalite?: string;
  telephone?: string;
  fax?: string;
  email?: string;
  patients?: IPatient[];
}

export class CaisseMaladie implements ICaisseMaladie {
  constructor(
    public id?: number,
    public numeroCaisseMaladie?: string,
    public nom?: string,
    public adresse?: string,
    public npaLocalite?: string,
    public telephone?: string,
    public fax?: string,
    public email?: string,
    public patients?: IPatient[]
  ) {}
}

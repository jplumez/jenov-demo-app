import { Moment } from 'moment';
import { IVaccination } from 'app/shared/model/vaccination.model';
import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';
import { IProfessionnelSante } from 'app/shared/model/professionnel-sante.model';

export interface IPatient {
  id?: number;
  noAvs?: string;
  dateNaissance?: Moment;
  nom?: string;
  prenom?: string;
  adresse?: string;
  npaLocalite?: string;
  telephone?: string;
  email?: string;
  numeroAssure?: string;
  vaccinations?: IVaccination[];
  caisseMaladie?: ICaisseMaladie;
  medecinTraitant?: IProfessionnelSante;
}

export class Patient implements IPatient {
  constructor(
    public id?: number,
    public noAvs?: string,
    public dateNaissance?: Moment,
    public nom?: string,
    public prenom?: string,
    public adresse?: string,
    public npaLocalite?: string,
    public telephone?: string,
    public email?: string,
    public numeroAssure?: string,
    public vaccinations?: IVaccination[],
    public caisseMaladie?: ICaisseMaladie,
    public medecinTraitant?: IProfessionnelSante
  ) {}
}

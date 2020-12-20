import { IPatient } from 'app/shared/model/patient.model';
import { ILocalite } from 'app/shared/model/localite.model';
import { IProfession } from 'app/shared/model/profession.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';

export interface IProfessionnelSante {
  id?: number;
  ide?: string;
  prenom?: string;
  nom?: string;
  sexe?: Sexe;
  rue?: string;
  patients?: IPatient[];
  localite?: ILocalite;
  profession?: IProfession;
}

export class ProfessionnelSante implements IProfessionnelSante {
  constructor(
    public id?: number,
    public ide?: string,
    public prenom?: string,
    public nom?: string,
    public sexe?: Sexe,
    public rue?: string,
    public patients?: IPatient[],
    public localite?: ILocalite,
    public profession?: IProfession
  ) {}
}

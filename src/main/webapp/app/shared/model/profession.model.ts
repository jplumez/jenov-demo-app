import { IProfessionnelSante } from 'app/shared/model/professionnel-sante.model';

export interface IProfession {
  id?: number;
  nom?: string;
  professionnelSantes?: IProfessionnelSante[];
}

export class Profession implements IProfession {
  constructor(public id?: number, public nom?: string, public professionnelSantes?: IProfessionnelSante[]) {}
}

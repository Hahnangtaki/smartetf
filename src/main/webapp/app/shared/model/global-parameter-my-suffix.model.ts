import { Moment } from 'moment';

export interface IGlobalParameterMySuffix {
  id?: number;
  prmId?: string;
  prmName?: string;
  prmType?: string;
  appType?: string;
  intVal?: number;
  floatVal?: number;
  strVal?: string;
  dateVal?: Moment;
  show?: boolean;
  edit?: boolean;
}

export const defaultValue: Readonly<IGlobalParameterMySuffix> = {
  show: false,
  edit: false
};

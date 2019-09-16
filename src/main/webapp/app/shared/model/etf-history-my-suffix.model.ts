import { Moment } from 'moment';

export interface IEtfHistoryMySuffix {
  id?: number;
  valueDate?: Moment;
  unitPrice?: number;
  unitQty?: number;
  aum?: number;
  etfProductId?: number;
}

export const defaultValue: Readonly<IEtfHistoryMySuffix> = {};

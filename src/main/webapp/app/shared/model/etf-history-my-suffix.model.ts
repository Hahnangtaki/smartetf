import { Moment } from 'moment';

export interface IEtfHistoryMySuffix {
  id?: number;
  productId?: number;
  valueDate?: Moment;
  unitPrice?: number;
  unitQty?: number;
  aum?: number;
}

export const defaultValue: Readonly<IEtfHistoryMySuffix> = {};

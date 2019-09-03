import { Moment } from 'moment';

export interface IEtfUnderlyingMySuffix {
  id?: number;
  underlyingId?: number;
  effectiveDate?: Moment;
  productId?: number;
}

export const defaultValue: Readonly<IEtfUnderlyingMySuffix> = {};

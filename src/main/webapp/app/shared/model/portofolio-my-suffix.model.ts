import { Moment } from 'moment';

export interface IPortofolioMySuffix {
  id?: number;
  customerId?: number;
  portofolioDate?: Moment;
  unit?: number;
  avgPrice?: number;
  etfProductId?: number;
}

export const defaultValue: Readonly<IPortofolioMySuffix> = {};

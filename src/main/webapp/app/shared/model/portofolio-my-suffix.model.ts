import { Moment } from 'moment';

export interface IPortofolioMySuffix {
  id?: number;
  customerId?: number;
  productId?: number;
  portofolioDate?: Moment;
  unit?: number;
  avgPrice?: number;
}

export const defaultValue: Readonly<IPortofolioMySuffix> = {};

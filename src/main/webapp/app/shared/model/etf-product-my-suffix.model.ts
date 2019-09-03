import { Moment } from 'moment';

export interface IEtfProductMySuffix {
  id?: number;
  productId?: string;
  productCode?: string;
  productName?: string;
  fundCategory?: string;
  inceptionDate?: Moment;
  clearingHouse?: string;
  exchage?: string;
  unitCreation?: number;
  unitPriceInitial?: number;
  unitPriceCurr?: number;
  lotPerBasket?: number;
  indexReff?: string;
  rating?: number;
  fee?: number;
  miId?: number;
  custodyId?: number;
}

export const defaultValue: Readonly<IEtfProductMySuffix> = {};

import { Moment } from 'moment';

export interface IRedemptionMySuffix {
  id?: number;
  redemptionId?: number;
  redemptionCode?: string;
  redemptionDate?: Moment;
  customerId?: string;
  customerName?: string;
  unitSellPriceInd?: number;
  unitSellPrice?: number;
  unitSellUnit?: number;
  unitSellLot?: number;
  unitSellBasket?: number;
  market?: string;
  sellGrossAmount?: number;
  fee?: number;
  sellNetAmount?: number;
  statusCash?: string;
  statusEtf?: string;
  status?: string;
  channel?: number;
  dealerId?: number;
  productId?: number;
  underlyingId?: number;
}

export const defaultValue: Readonly<IRedemptionMySuffix> = {};

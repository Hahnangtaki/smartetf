import { Moment } from 'moment';

export interface ISubscriptMySuffix {
  id?: number;
  subscriptCode?: string;
  subscriptDate?: Moment;
  customerId?: string;
  customerName?: string;
  unitBuyPriceInd?: number;
  unitBuyPrice?: number;
  unitBuyUnit?: number;
  unitBuyLot?: number;
  unitBuyBasket?: number;
  market?: string;
  buyGrossAmount?: number;
  fee?: number;
  buyNetAmount?: number;
  statusCash?: string;
  statusEtf?: string;
  status?: string;
  channel?: number;
  etfProductId?: number;
  dealerParticipantId?: number;
  etfExecutionDtlId?: number;
}

export const defaultValue: Readonly<ISubscriptMySuffix> = {};

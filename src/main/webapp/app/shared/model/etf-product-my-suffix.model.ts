import { Moment } from 'moment';
import { IEtfUnderlyingMySuffix } from 'app/shared/model/etf-underlying-my-suffix.model';
import { IEtfHistoryMySuffix } from 'app/shared/model/etf-history-my-suffix.model';
import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';
import { IPortofolioMySuffix } from 'app/shared/model/portofolio-my-suffix.model';
import { IDealerParticipantMySuffix } from 'app/shared/model/dealer-participant-my-suffix.model';

export interface IEtfProductMySuffix {
  id?: number;
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
  etfUnderlyings?: IEtfUnderlyingMySuffix[];
  etfHistories?: IEtfHistoryMySuffix[];
  subscripts?: ISubscriptMySuffix[];
  redemptions?: IRedemptionMySuffix[];
  portofolios?: IPortofolioMySuffix[];
  dealerParticipants?: IDealerParticipantMySuffix[];
  miId?: number;
  bankCustodyId?: number;
}

export const defaultValue: Readonly<IEtfProductMySuffix> = {};

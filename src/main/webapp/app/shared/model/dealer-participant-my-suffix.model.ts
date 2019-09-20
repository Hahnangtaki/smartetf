import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';
import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';

export interface IDealerParticipantMySuffix {
  id?: number;
  dealerCode?: string;
  dealerName?: string;
  redemptions?: IRedemptionMySuffix[];
  subscripts?: ISubscriptMySuffix[];
  etfProducts?: IEtfProductMySuffix[];
}

export const defaultValue: Readonly<IDealerParticipantMySuffix> = {};

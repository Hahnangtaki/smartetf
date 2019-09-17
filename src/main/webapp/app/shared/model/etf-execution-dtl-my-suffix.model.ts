import { ISubscriptMySuffix } from 'app/shared/model/subscript-my-suffix.model';
import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';

export interface IEtfExecutionDtlMySuffix {
  id?: number;
  transactionType?: string;
  subscripts?: ISubscriptMySuffix[];
  redemptions?: IRedemptionMySuffix[];
  executeId?: number;
}

export const defaultValue: Readonly<IEtfExecutionDtlMySuffix> = {};

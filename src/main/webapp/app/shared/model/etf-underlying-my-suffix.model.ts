import { Moment } from 'moment';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';

export interface IEtfUnderlyingMySuffix {
  id?: number;
  effectiveDate?: Moment;
  etfProductId?: number;
  etfUnderlyingDtls?: IEtfUnderlyingDtlMySuffix[];
}

export const defaultValue: Readonly<IEtfUnderlyingMySuffix> = {};

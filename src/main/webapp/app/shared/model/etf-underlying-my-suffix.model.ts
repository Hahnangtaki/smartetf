import { Moment } from 'moment';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';

export interface IEtfUnderlyingMySuffix {
  id?: number;
  effectiveDate?: Moment;
  active?: boolean;
  etfUnderlyingDtls?: IEtfUnderlyingDtlMySuffix[];
  etfProductId?: number;
}

export const defaultValue: Readonly<IEtfUnderlyingMySuffix> = {
  active: false
};

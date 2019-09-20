import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';

export interface IEtfExecutionMySuffix {
  id?: number;
  basketOrderId?: number;
  stockCode?: string;
  qty?: number;
  qtyOdd?: number;
  price?: number;
  amount?: number;
  etfExecutionDtls?: IEtfExecutionDtlMySuffix[];
}

export const defaultValue: Readonly<IEtfExecutionMySuffix> = {};
